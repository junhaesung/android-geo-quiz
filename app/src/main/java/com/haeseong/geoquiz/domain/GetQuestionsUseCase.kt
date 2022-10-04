package com.haeseong.geoquiz.domain

import javax.inject.Inject

class GetQuestionsUseCase @Inject constructor(
    private val questionRepository: QuestionRepository,
) {
    suspend operator fun invoke(page: Int, size: Int): List<Question> =
        questionRepository.getQuestions(page, size)
}