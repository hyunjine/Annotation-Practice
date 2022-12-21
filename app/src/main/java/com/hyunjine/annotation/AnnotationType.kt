package com.hyunjine.annotation

import androidx.annotation.IntRange

class AnnotationType {

    @Deprecated(":", ReplaceWith("functionNew1()"))
    fun function1(a: Int, b: Int) : Int {
        return a + b
    }

    fun functionNew1(a: Int, b: Int) : Int {
        return a + b + 1
    }

    fun function2(@IntRange(from = 0, to = 10) a: Int, b: Int) : Int {
        return a + b + 1
    }
}