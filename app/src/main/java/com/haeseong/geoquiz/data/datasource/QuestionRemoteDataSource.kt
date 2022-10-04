package com.haeseong.geoquiz.data.datasource

import com.haeseong.geoquiz.data.QuestionEntity
import retrofit2.Response

interface QuestionRemoteDataSource {
    suspend fun getQuestions(page: Int, size: Int): Response<List<QuestionEntity>>
}