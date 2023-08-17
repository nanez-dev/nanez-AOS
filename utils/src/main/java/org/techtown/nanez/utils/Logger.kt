package org.techtown.nanez.utils

import android.content.Intent
import android.util.Log

/**
 * Created by iseungjun on 2023/08/17
 */
object Logger {
    private const val LEVEL = Log.VERBOSE
    private const val TAG = "NANEZ_LOG"
    private const val CLASS_STACK_DEPTH_INDEX = 4

    @JvmStatic
    fun e(t: Throwable? = null, msg: String? = "") {
        println(TAG, Log.ERROR, buildLogMsg(msg), t)
    }

    @JvmStatic
    fun e(tag: String? = TAG, t: Throwable? = null, msg: String? = "") {
        println(tag, Log.ERROR, buildLogMsg(msg), t)
    }

    @JvmStatic
    fun w(msg: String) {
        println(TAG, Log.WARN, buildLogMsg(msg))
    }

    @JvmStatic
    fun w(tag: String? = TAG, msg: String) {
        println(tag, Log.WARN, buildLogMsg(msg))
    }

    @JvmStatic
    fun i(msg: String) {
        println(TAG, Log.INFO, buildLogMsg(msg), null)
    }

    @JvmStatic
    fun i(tag: String? = TAG, msg: String) {
        println(tag, Log.INFO, buildLogMsg(msg), null)
    }

    @JvmStatic
    fun d(msg: String) {
        println(TAG, Log.DEBUG, buildLogMsg(msg), null)
    }

    @JvmStatic
    fun d(tag: String, msg: String) {
        println(tag, Log.DEBUG, buildLogMsg(msg), null)
    }

    @JvmStatic
    fun v(msg: String) {
        println(TAG, Log.VERBOSE, buildLogMsg(msg), null)
    }

    @JvmStatic
    fun v(tag: String? = TAG, msg: String) {
        println(tag, Log.VERBOSE, buildLogMsg(msg), null)
    }

    private fun println(tag: String? = TAG, level: Int, msg: String?, t: Throwable? = null) {
        var resultMsg = msg ?: ""
        if (level < LEVEL) {
            return
        }

        if (t != null) {
            resultMsg = """$msg\n${Log.getStackTraceString(t)}""".trimIndent()
        }
        Log.println(level, tag, resultMsg)
    }


    private fun buildLogMsg(message: String?): String {
        val ste = Thread.currentThread().stackTrace[CLASS_STACK_DEPTH_INDEX]
        val sb = StringBuilder()
        sb.append(ste.fileName)
        sb.append(":")
        sb.append(ste.methodName)
        sb.append(":")
        sb.append(ste.lineNumber)
        sb.append(">")
        sb.append(message ?: "")
        return sb.toString()
    }

    fun printIntent(i: Intent?) = printIntent(TAG, i)

    @JvmStatic
    fun printIntent(tag: String, i: Intent?) {
        try {
            d(tag, "-------------------------------------------------------")
            d(tag, "intent = $i")
            if (i != null) {
                val extras = i.extras
                d(tag, "extras = $extras")
                if (extras != null) {
                    val keys: Set<*> = extras.keySet()
                    d(tag, "++ bundle key count = " + keys.size)
                    for (_key in extras.keySet()) {
                        d(tag, "key=" + _key + " : " + extras[_key])
                    }
                }
            }
        } catch (e: Exception) {
            e(tag, e, e.toString())
        } finally {
            d(tag, "-------------------------------------------------------")
        }
    }
}
