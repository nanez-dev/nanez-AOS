package com.nane.network

import okhttp3.*
import okhttp3.internal.http.promisesBody
import okhttp3.internal.platform.Platform
import okio.Buffer
import okio.GzipSource
import java.io.EOFException
import java.io.IOException
import java.nio.charset.Charset
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.random.Random.Default.nextInt


class RetrofitLogInterceptor @JvmOverloads constructor(private val logger: Logger = Logger.DEFAULT) : Interceptor {
    enum class Level {
        /**
         * No logs.
         */
        NONE,

        /**
         * Logs request and response lines.
         *
         *
         * Example:
         * <pre>`--> POST /greeting http/1.1 (3-byte body)
         *
         * <-- 200 OK (22ms, 6-byte body)
        `</pre> *
         */
        BASIC,

        /**
         * Logs request and response lines and their respective headers.
         *
         *
         * Example:
         * <pre>`--> POST /greeting http/1.1
         * Host: example.com
         * Content-Type: plain/text
         * Content-Length: 3
         * --> END POST
         *
         * <-- 200 OK (22ms)
         * Content-Type: plain/text
         * Content-Length: 6
         * <-- END HTTP
        `</pre> *
         */
        HEADERS,

        /**
         * Logs request and response lines and their respective headers and bodies (if present).
         *
         *
         * Example:
         * <pre>`--> POST /greeting http/1.1
         * Host: example.com
         * Content-Type: plain/text
         * Content-Length: 3
         *
         * Hi?
         * --> END POST
         *
         * <-- 200 OK (22ms)
         * Content-Type: plain/text
         * Content-Length: 6
         *
         * Hello!
         * <-- END HTTP
        `</pre> *
         */
        BODY
    }

    interface Logger {
        fun log(requestId: String?, message: String?)

        companion object {
            /**
             * A [Logger] defaults output appropriate for the current platform.
             */
            val DEFAULT: Logger = object : Logger {
                override fun log(requestId: String?, message: String?) {
                    message?.let {
                        Platform.get().log(message, Platform.INFO, null)
                    }
                }
            }
        }
    }

    @Volatile
    private var headersToRedact = emptySet<String>()
    fun redactHeader(name: String) {
        val newHeadersToRedact: MutableSet<String> = TreeSet(java.lang.String.CASE_INSENSITIVE_ORDER)
        newHeadersToRedact.addAll(headersToRedact)
        newHeadersToRedact.add(name)
        headersToRedact = newHeadersToRedact
    }

    @Volatile
    var level = Level.NONE
        private set

    /**
     * Change the level at which this interceptor logs.
     */
    fun setLevel(level: Level?): RetrofitLogInterceptor {
        if (level == null) throw NullPointerException("level == null. Use Level.NONE instead.")
        this.level = level
        return this
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val level = level
        val requestId: String
        val request: Request = chain.request()
        requestId = nextInt(10000).toString() + " & " + request.url.encodedPath
        if (level == Level.NONE) {
            return chain.proceed(request)
        }
        val logBody = level == Level.BODY
        val logHeaders = logBody || level == Level.HEADERS
        val requestBody = request.body
        val hasRequestBody = requestBody != null
        val connection: Connection? = chain.connection()
        var requestStartMessage = ("--> "
            + request.method
            + ' ' + request.url
            + if (connection != null) " " + connection.protocol() else "")
        if (!logHeaders && hasRequestBody) {
            requestStartMessage += " (" + requestBody?.contentLength() + "-byte body)"
        }
        logger.log(requestId, requestStartMessage)
        if (logHeaders) {
            if (hasRequestBody) {
                // Request body headers are only present when installed as a network interceptor. Force
                // them to be included (when available) so there values are known.
                if (requestBody?.contentType() != null) {
                    logger.log(requestId, "Content-Type: " + requestBody.contentType())
                }
                if (requestBody?.contentLength() != -1L) {
                    logger.log(requestId, "Content-Length: " + requestBody?.contentLength())
                }
            }
            val headers = request.headers
            var i = 0
            val count = headers.size
            while (i < count) {
                val name = headers.name(i)
                // Skip headers from the request body as they are explicitly logged above.
                if (!"Content-Type".equals(name, ignoreCase = true) && !"Content-Length".equals(name, ignoreCase = true)) {
                    logHeader(requestId, headers, i)
                }
                i++
            }
            if (!logBody || !hasRequestBody) {
                logger.log(requestId, "--> END " + request.method)
            } else if (bodyHasUnknownEncoding(request.headers)) {
                logger.log(requestId, "--> END " + request.method + " (encoded body omitted)")
            } else {
                val buffer = Buffer()
                requestBody?.writeTo(buffer)
                var charset = UTF8
                val contentType = requestBody?.contentType()
                if (contentType != null) {
                    charset = contentType.charset(UTF8)
                }
                logger.log(requestId, "")
                if (isPlaintext(buffer)) {
                    logger.log(requestId, buffer.readString(charset))
                    logger.log(requestId, "--> END " + request.method
                        + " (" + requestBody?.contentLength() + "-byte body)")
                } else {
                    logger.log(requestId, "--> END " + request.method + " (binary "
                        + requestBody?.contentLength() + "-byte body omitted)")
                }
            }
        }
        val startNs = System.nanoTime()
        val response: Response = try {
            chain.proceed(request)
        } catch (e: Exception) {
            logger.log(requestId, "<-- HTTP FAILED: $e")
            throw e
        }
        val tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs)
        val responseBody = response.body
        val contentLength = responseBody?.contentLength()
        val bodySize = if (contentLength != -1L) "$contentLength-byte" else "unknown-length"
        logger.log(requestId, "<-- "
            + response.code
            + (if (response.message.isEmpty()) "" else ' '.toString() + response.message)
            + ' ' + response.request.url
            + " (" + tookMs + "ms" + (if (!logHeaders) ", $bodySize body" else "") + ')')
        if (logHeaders) {
            val headers = response.headers
            var i = 0
            val count = headers.size
            while (i < count) {
                logHeader(requestId, headers, i)
                i++
            }
            if (!logBody || !response.promisesBody()) {
                logger.log(requestId, "<-- END HTTP")
            } else if (bodyHasUnknownEncoding(response.headers)) {
                logger.log(requestId, "<-- END HTTP (encoded body omitted)")
            } else {
                val source = responseBody?.source()
                source?.let {
                    source.request(Long.MAX_VALUE) // Buffer the entire body.
                    var buffer = source.buffer
                    var gzippedLength: Long? = null
                    if ("gzip".equals(headers["Content-Encoding"], ignoreCase = true)) {
                        gzippedLength = buffer.size
                        var gzippedResponseBody: GzipSource? = null
                        try {
                            gzippedResponseBody = GzipSource(buffer.clone())
                            buffer = Buffer()
                            buffer.writeAll(gzippedResponseBody)
                        } finally {
                            gzippedResponseBody?.close()
                        }
                    }
                    var charset = UTF8
                    val contentType = responseBody.contentType()
                    if (contentType != null) {
                        charset = contentType.charset(UTF8)
                    }
                    if (!isPlaintext(buffer)) {
                        logger.log(requestId, "")
                        logger.log(requestId, "<-- END HTTP (binary " + buffer.size + "-byte body omitted)")
                        return response
                    }
                    if (contentLength != 0L) {
                        logger.log(requestId, "")
                        logger.log(requestId, buffer.clone().readString(charset))
                    }
                    if (gzippedLength != null) {
                        logger.log(requestId, "<-- END HTTP (" + buffer.size + "-byte, "
                            + gzippedLength + "-gzipped-byte body)")
                    } else {
                        logger.log(requestId, "<-- END HTTP (" + buffer.size + "-byte body)")
                    }
                }
            }
        }
        return response
    }

    private fun logHeader(requestId: String, headers: Headers, i: Int) {
        val value = if (headersToRedact.contains(headers.name(i))) "██" else headers.value(i)
        logger.log(requestId, headers.name(i) + ": " + value)
    }

    companion object {
        private val UTF8 = Charset.forName("UTF-8")

        /**
         * Returns true if the body in question probably contains human readable text. Uses a small sample
         * of code points to detect unicode control characters commonly used in binary file signatures.
         */
        fun isPlaintext(buffer: Buffer): Boolean {
            return try {
                val prefix = Buffer()
                val byteCount = if (buffer.size < 64) buffer.size else 64
                buffer.copyTo(prefix, 0, byteCount)
                for (i in 0..15) {
                    if (prefix.exhausted()) {
                        break
                    }
                    val codePoint = prefix.readUtf8CodePoint()
                    if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                        return false
                    }
                }
                true
            } catch (e: EOFException) {
                false // Truncated UTF-8 sequence.
            }
        }

        private fun bodyHasUnknownEncoding(headers: Headers): Boolean {
            val contentEncoding = headers["Content-Encoding"]
            return (contentEncoding != null && !contentEncoding.equals("identity", ignoreCase = true)
                && !contentEncoding.equals("gzip", ignoreCase = true))
        }
    }
}