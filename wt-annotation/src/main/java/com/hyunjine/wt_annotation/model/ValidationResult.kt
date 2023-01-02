package com.hyunjine.wt_annotation.model

data class ValidationResult(
    var isValid: Boolean = true,
    val invalidFieldNameAndTags: MutableList<FieldNameAndTag> = mutableListOf()
)