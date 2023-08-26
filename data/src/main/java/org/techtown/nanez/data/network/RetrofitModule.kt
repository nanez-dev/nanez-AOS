package org.techtown.nanez.data.network

import android.text.TextUtils
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import org.techtown.nanez.utils.Logger
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.CookieManager
import java.net.CookiePolicy
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by iseungjun on 2023/08/17
 */
@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    private const val BASE_URL = "https://api.nanez.co.kr/"
    private const val RETROFIT_TIMEOUT = 15.toLong()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(RETROFIT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(RETROFIT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(RETROFIT_TIMEOUT, TimeUnit.SECONDS)
            .cookieJar(JavaNetCookieJar(CookieManager().apply { setCookiePolicy(CookiePolicy.ACCEPT_ALL) }))
            .addNetworkInterceptor(logInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setPrettyPrinting().create()))
            .client(okHttpClient)
            .build()
    }

    private fun logInterceptor(): Interceptor {
        return RetrofitLogInterceptor(object : RetrofitLogInterceptor.Logger {
            override fun log(requestId: String?, message: String?) {
                message?.let {
                    val requestTagId = "retrofit${requestId}"
                    printLog(requestTagId, message)
                }
            }
        }).apply {
            setLevel(RetrofitLogInterceptor.Level.BODY)
        }
    }

    private fun printLog(requestTagId: String, msg: String) {
        if (!TextUtils.isEmpty(msg)) {
            when {
                msg.startsWith("Content-Type") or
                    msg.startsWith("content-type") or
                    msg.startsWith("content-length") or
                    msg.startsWith("Content-Length") or
                    msg.startsWith("date:") or
                    msg.startsWith("access-control-allow") or
                    msg.startsWith("x-cache") or
                    msg.startsWith("via") or
                    msg.startsWith("cache-control") or
                    msg.startsWith("x-request-id") or
                    msg.startsWith("vary") or
                    msg.startsWith("set-cookie") or
                    msg.startsWith("x-amz-cf") -> {
                    return
                }
            }

            val message = try {
                when {
                    msg.startsWith("{") -> {
                        val jsonObject = JSONObject(msg)
                        jsonObject.toString(4)
                    }

                    msg.startsWith("[") -> {
                        val jsonArray = JSONArray(msg)
                        jsonArray.toString(4)
                    }

                    else -> msg
                }
            } catch (e: JSONException) {
                e.printStackTrace()
                msg
            }

            System.getProperty("line.separator")?.let { separator ->
                val lines = message.split((separator).toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                Logger.d(requestTagId, "╔═══════════════════════════════════════════════════════════════════════════════════════")
                for (line in lines) {
                    val removedEscapeMsg = line.replace("\\\\".toRegex(), "")
                    Logger.d(requestTagId, removedEscapeMsg)
                }

                Logger.d(requestTagId, "╚═══════════════════════════════════════════════════════════════════════════════════════")
            }
        }
    }
}