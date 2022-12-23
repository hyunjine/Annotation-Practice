package com.hyunjine.annotation.util

import android.util.Log

const val TAG: String = "winter"

fun logger(message: Any?) {
    Log.d(TAG, message.toString())
}

object Logger {
    fun v(tag: String = "winter", message: Any?) {
        logger(message)
    }
}