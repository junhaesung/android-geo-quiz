package com.haeseong.geoquiz.domain

interface QuestionRepository {
    suspend fun getQuestions(page: Int, size: Int): List<Question>
}