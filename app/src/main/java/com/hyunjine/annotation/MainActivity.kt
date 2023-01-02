package com.hyunjine.annotation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.datavalidation.generated.validate
import com.hyunjine.annotation.util.Logger
import com.hyunjine.wt_annotation.annotation.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val book = Book(
            "힐링페이퍼 인재상", 0, "Thomas", "thomas@healingpaper/com",
            Publisher("높은 기준을 추구합니다. 소신있게 반대하고 헌신합니다. 틀릴 수도 있다고 생각합니다.")
        )


        val validationResult = book.validate()
        Logger.v("Validation",
            StringBuilder()
                .appendLine("유효성: ${validationResult.isValid}")
                .appendLine(
                    "잘못된 필드: ${
                        validationResult.invalidFieldNameAndTags.joinToString(
                            ", ",
                            transform = { it.fieldName })
                    }"
                )
                .appendLine(
                    "메시지: ${
                        validationResult.invalidFieldNameAndTags.joinToString(
                            " & ",
                            transform = { it.tag })
                    }"
                )
                .toString()
        )
    }
}

@DataValidation
data class Book(
    @MinLength(10, "title length minimum is 10")
    @MaxLength(50, "title length maximum is 50")
    val title: String,
    @MinValue(1, "book is not free")
    @MaxValue(100000, "book is too expensive")
    val price: Int,
    @MaxLength(10, "author name is too long")
    val authorName: String,
    @Regex("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$", "authorEmail is invalid")
    val authorEmail: String,
    @NotNull("publisher should is null")
    @Nested
    val publisher: Publisher
)

@DataValidation
data class Publisher(
    @MaxLength(10, "publisher name is too long")
    val name: String
)