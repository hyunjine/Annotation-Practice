package com.hyunjine.annotation

import android.os.Bundle
import android.view.View
import androidx.annotation.*
import androidx.appcompat.app.AppCompatActivity
import com.hyunjine.annotation.util.Logger
import com.hyunjine.annotation.util.TAG
import kotlin.reflect.KMutableProperty
import kotlin.reflect.KVisibility
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.memberFunctions
import kotlin.reflect.full.memberProperties

class MainActivity : AppCompatActivity() {
    private val annotationType: AnnotationType by lazy { AnnotationType() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        kClassTest()

        test()
    }

    fun test() {
        val data = Data(
            "극도의 투명함",
            "투명함은 단순히 결과가 잘 공유되는 것 이상을 의미합니다. 우리는 결과뿐 아니라 의도와 맥락, 과정까지도 알 수 있을 만큼 공유하는 것을 투명하다고 합니다. 우리가 추구하는 극도의 솔직함은 투명함이 전제되어야만 발현된다고 믿습니다."
        )

        val validationResult = FieldValidator.validate(data)
        Logger.v(message = "유효성: ${validationResult.isValid}  유효하지않은 Field: ${validationResult.invalidFieldNames}")
    }

    data class Data(
        @StringConstraint(10, 50)
        val title: String,
        @StringConstraint(100, 500)
        val contents: String
    )

    @Target(AnnotationTarget.PROPERTY, AnnotationTarget.CLASS)
    @IntDef(View.GONE, View.VISIBLE, View.INVISIBLE)
    @Retention(AnnotationRetention.RUNTIME)
    annotation class Visibility


    @Target(AnnotationTarget.PROPERTY)
    annotation class StringConstraint(
        val minLength: Int,
        val maxLength: Int
    )

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
        var visibility: Int = View.GONE,

        @StringConstraint(1, 2)
        var test: String
    )

    fun kClassTest() {
        val receiverA = A()
        val kClass = A::class

        //Class Meta Data
        Logger.v(TAG, kClass.simpleName!!)
        Logger.v(TAG, kClass.isData)
        Logger.v(TAG, kClass.isAbstract)
        Logger.v(TAG, kClass.isCompanion)
        Logger.v(TAG, kClass.isFinal)
        Logger.v(TAG, kClass.isInner)
        Logger.v(TAG, kClass.isOpen)
        Logger.v(TAG, kClass.isSealed)

        //Create Instance
        Logger.v(TAG, kClass.createInstance())

        //Constructors
        Logger.v(TAG, kClass.constructors.map { it.name })
        Logger.v(TAG, kClass.constructors.map { it.parameters })
        Logger.v(TAG, kClass.constructors.map { it.call() })

        //Annotations
        Logger.v(TAG, kClass.annotations.map { it.annotationClass.simpleName })

        //Fields
        val kProperty = kClass.memberProperties.find { it.name == "field1" }
        (kProperty as KMutableProperty<*>).setter.call(receiverA, "Changed Field1 Value")
        Logger.v(TAG, receiverA.field1)

        //Functions
        val a = kClass.memberProperties.find { it.name == "d" }


        Logger.v(TAG, kClass.memberFunctions.find { it.name == "function3" }?.visibility == KVisibility.PRIVATE)
        Logger.v(TAG, kClass.memberFunctions.find { it.name == "function3" }?.call(receiverA) ?: "")
    }

    companion object {
        const val ALERT_DIALOG_TITLE_DEFAULT_SIZE: Float = 24F
    }
}