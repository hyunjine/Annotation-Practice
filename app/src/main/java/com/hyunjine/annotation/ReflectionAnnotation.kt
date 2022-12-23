package com.hyunjine.annotation

import com.hyunjine.annotation.util.Logger
import com.hyunjine.annotation.util.TAG
import com.hyunjine.annotation.util.logger
import kotlin.reflect.KMutableProperty
import kotlin.reflect.KVisibility
import kotlin.reflect.full.*

class A {
    var field1 = ""
    var field2 = 0
    fun function1() {}
    fun function2() {}

    private fun function3() {}
}

object FieldValidator {
    data class ValidationResult(
        var isValid: Boolean = true,
        val invalidFieldNames: MutableList<String> = mutableListOf()
    )

    fun validate(data: Any): ValidationResult {
        val result = ValidationResult()

        data::class.declaredMemberProperties.forEach {
            val field = it
            val annotation = it.findAnnotation<MainActivity.StringConstraint>()

            if (annotation != null && field.getter.visibility == KVisibility.PUBLIC) {
                val fieldValue = field.getter.call(data) as String
                if (fieldValue.length !in annotation.minLength..annotation.maxLength) {
                    result.isValid = false
                    result.invalidFieldNames.add(field.name)
                }
            }
        }

        return result
    }
}
