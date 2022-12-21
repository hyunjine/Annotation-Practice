package com.hyunjine.annotation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    private val annotationType: AnnotationType by lazy { AnnotationType() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val result = annotationType.functionA(1, 2)
        logger(result)
    }
}