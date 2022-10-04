package com.haeseong.geoquiz.data

data class QuestionEntity(
    val questionId: Long,
    val content: String,
    val answer: Boolean,
)
