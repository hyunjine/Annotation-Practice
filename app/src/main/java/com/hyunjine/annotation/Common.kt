package com.hyunjine.annotation

import android.util.Log

private const val TAG: String = "winter"

fun logger(message: Any?) {
    Log.d(TAG, message.toString())
}