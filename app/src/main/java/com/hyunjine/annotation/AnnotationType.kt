package com.hyunjine.annotation

class AnnotationType {

    @Deprecated(":", ReplaceWith("functionB"))
    fun functionA(a: Int, b: Int) : Int {
        return a + b
    }

    fun functionB(a: Int, b: Int) : Int {
        return a + b + 1
    }
}