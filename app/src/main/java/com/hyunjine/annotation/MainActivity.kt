package com.hyunjine.annotation

import android.os.Bundle
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.FloatRange
import androidx.annotation.FontRes
import androidx.annotation.IntDef
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val annotationType: AnnotationType by lazy { AnnotationType() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        AlertDialogAttr(visibility = 8)


    }


    @Target(AnnotationTarget.PROPERTY)
    @IntDef(View.GONE, View.VISIBLE, View.INVISIBLE)
    @Retention(AnnotationRetention.RUNTIME)
    annotation class Visibility


    data class AlertDialogAttr(
        @FloatRange(from = 0.0, to = 48.0)
        val textSize: Float = ALERT_DIALOG_TITLE_DEFAULT_SIZE,
        @FontRes
        var fontId: Int = R.font.alert_dialog_title_default,
        @StringRes
        val titleStrId: Int = R.string.alert_dialog_title_default,
        @ColorRes
        var colorId: Int = R.color.alert_dialog_title_default,
        @Visibility


        var visibility: Int = View.GONE
    )

    companion object {
        const val ALERT_DIALOG_TITLE_DEFAULT_SIZE: Float = 24F
    }
}