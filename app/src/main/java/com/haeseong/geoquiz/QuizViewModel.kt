package com.haeseong.geoquiz

import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel() {

    val mQuestions = listOf(
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true),
    )

    private var mCurrentIndex = 0

    fun prev() {
        mCurrentIndex -= 1
        mCurrentIndex += mQuestions.size
        mCurrentIndex %= mQuestions.size
    }

    fun next() {
        mCurrentIndex += 1
        mCurrentIndex %= mQuestions.size
    }

    fun getQuestionResourceId(): Int = mQuestions[mCurrentIndex].mTextResId

    fun isAnswerTrue(): Boolean = mQuestions[mCurrentIndex].mAnswerTrue
}