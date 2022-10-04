package com.haeseong.geoquiz.data.datasource

import com.haeseong.geoquiz.data.QuestionApi
import com.haeseong.geoquiz.data.QuestionEntity
import retrofit2.Response
import javax.inject.Inject

class QuestionRemoteDataSourceImpl @Inject constructor(
    private val questionApi: QuestionApi
) : QuestionRemoteDataSource {

    override suspend fun getQuestions(page: Int, size: Int): Response<List<QuestionEntity>> =
        questionApi.getQuestions(page, size)
}