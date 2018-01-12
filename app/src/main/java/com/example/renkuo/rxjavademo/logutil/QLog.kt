package com.example.renkuo.rxjavademo.logutil

import android.util.Log
import java.io.PrintWriter
import java.io.StringWriter

/**
 * Created by renkuo on 2017/12/26.
 */

object QLog {
    val NONE: Byte = 0
    val ERRORS_ONLY: Byte = 1
    val ERRORS_WARNINGS: Byte = 2
    val ERRORS_WARNINGS_INFO: Byte = 3
    val ERRORS_WARNINGS_INFO_DEBUG: Byte = 4
    val ALL: Byte = 5
    var LOGGING_LEVEL = ALL

    /*
     * For filtering app specific output
     */
    private val TAG = "QLog"

    val isVLoggable: Boolean
        get() = LOGGING_LEVEL > ERRORS_WARNINGS_INFO_DEBUG

    val isDLoggable: Boolean
        get() = LOGGING_LEVEL > ERRORS_WARNINGS_INFO

    val isILoggable: Boolean
        get() = LOGGING_LEVEL > ERRORS_WARNINGS

    val isWLoggable: Boolean
        get() = LOGGING_LEVEL > ERRORS_ONLY

    val isELoggable: Boolean
        get() = LOGGING_LEVEL > NONE

    //自定义访问器
    private val trace: String
        get() {
            val depth = 2
            val t = Throwable()
            val elements = t.stackTrace
            val callerMethodName = elements[depth].methodName
            val callerClassPath = elements[depth].className
            val lineNo = elements[depth].lineNumber
            val i = callerClassPath.lastIndexOf('.')
            val callerClassName = callerClassPath.substring(i + 1)
            return "$callerClassName: $callerMethodName() [$lineNo] - "
        }
    /*
     * So any important logs can be outputted in non filtered output also
     */
    //    private static final String TAG_GENERAL_OUTPUT = "QLog";

    //    static {
    //        i("Log class reloaded");
    //    }

    fun e(tag: String, obj: Any, cause: Throwable) {
        if (isELoggable) {
            Log.e(tag, trace + getThrowableTrace(cause) + obj.toString())
        }
    }

    /**
     * @param obj
     * @param cause The exception which caused this error, may not be null
     */
    fun e(obj: Any, cause: Throwable) {
        if (isELoggable) {
            Log.e(TAG, trace + obj.toString())
            Log.e(TAG, getThrowableTrace(cause))
            //            Log.e(TAG_GENERAL_OUTPUT, getTrace() + String.valueOf(obj));
            //            Log.e(TAG_GENERAL_OUTPUT, getThrowableTrace(cause));
        }
    }

    fun e(obj: Any) {
        if (isELoggable) {
            Log.e(TAG, trace + obj.toString())
            //Log.e(TAG_GENERAL_OUTPUT, getTrace() + String.valueOf(obj));
        }
    }

    fun w(obj: Any, cause: Throwable) {
        if (isWLoggable) {
            Log.w(TAG, trace + obj.toString())
            Log.w(TAG, getThrowableTrace(cause))
            //            Log.w(TAG_GENERAL_OUTPUT, getTrace() + String.valueOf(obj));
            //            Log.w(TAG_GENERAL_OUTPUT, getThrowableTrace(cause));
        }
    }

    fun w(obj: Any) {
        if (isWLoggable) {
            Log.w(TAG, trace + obj.toString())
            //Log.w(TAG_GENERAL_OUTPUT, getTrace() + String.valueOf(obj));
        }
    }

    fun i(obj: Any) {
        if (isILoggable) {
            Log.i(TAG, trace + obj.toString())
        }
    }

    fun d(obj: Any) {
        if (isDLoggable) {
            Log.d(TAG, trace + obj.toString())
        }
    }

    fun v(obj: Any) {
        if (isVLoggable) {
            Log.v(TAG, trace + obj.toString())
        }
    }

    fun setLoggingEnable(isEnable: Boolean) {
        LOGGING_LEVEL = if (isEnable) ALL else -5
    }

    private fun getThrowableTrace(thr: Throwable): String {
        val b = StringWriter()
        thr.printStackTrace(PrintWriter(b))
        return b.toString()
    }

    /**
     * Prints the stack trace to mubaloo log and standard log
     * @param e
     */
    fun handleException(e: Exception) {
        QLog.e(e.toString())
        e.printStackTrace()
    }
}