package com.haeseong.geoquiz.data

import com.haeseong.geoquiz.data.datasource.QuestionRemoteDataSource
import com.haeseong.geoquiz.domain.Question
import com.haeseong.geoquiz.domain.QuestionRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QuestionRepositoryImpl @Inject constructor(
    private val questionRemoteDataSource: QuestionRemoteDataSource
) : QuestionRepository {
    override suspend fun getQuestions(page: Int, size: Int): List<Question> {
        return try {
            val response = withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
                questionRemoteDataSource.getQuestions(page, size)
            }
            println("response: $response")
            if (response.isSuccessful && response.body() != null) {
                (response.body() as List<QuestionEntity>).map {
                    QuestionMapper.toQuestion(it)
                }
            } else {
                listOf()
            }
        } catch (e: Exception) {
            println("exception: ${e.message}")
            listOf()
        }
    }
}