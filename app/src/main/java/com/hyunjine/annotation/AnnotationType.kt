package com.hyunjine.annotation

import androidx.annotation.FloatRange
import androidx.annotation.IntRange

class AnnotationType {
    @Deprecated("Error Logic", ReplaceWith("functionNew1(a, b)"))
    fun function1(a: Int, b: Int) : Int {
        return a + b
    }

    fun functionNew1(a: Int, b: Int) : Int {
        return a + b + 1
    }

    fun function2(@IntRange(from = 0, to = 10) a: Int, b: Int) : Int {
        return a + b + 1
    }

    fun function2(@FloatRange(from = 0.0, to = 10.0) a: Float, b: Float) : Float {
        return a + b
    }

    fun function3 (a: Int = 0, b: Int = 0) : Int {
        return a + b
    }

    @JvmOverloads
    fun function4 (a: Int = 0, b: Int = 0) : Int {
        return a + b
    }
}