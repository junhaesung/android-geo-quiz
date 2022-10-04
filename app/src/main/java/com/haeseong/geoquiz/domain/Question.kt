package com.haeseong.geoquiz.domain

data class Question(
    val questionId: Long,
    val content: String,
    val answer: Boolean,
)