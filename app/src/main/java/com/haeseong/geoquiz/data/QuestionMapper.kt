package com.haeseong.geoquiz.data

import com.haeseong.geoquiz.domain.Question

object QuestionMapper {
    fun toQuestion(questionEntity: QuestionEntity) = Question(
        questionId = questionEntity.questionId,
        content = questionEntity.content,
        answer = questionEntity.answer,
    )
}