package com.haeseong.geoquiz.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuestionApi {
    @GET("v1/questions")
    suspend fun getQuestions(
        @Query("page") page: Int,
        @Query("size") size: Int = 20,
    ): Response<List<QuestionEntity>>
}
