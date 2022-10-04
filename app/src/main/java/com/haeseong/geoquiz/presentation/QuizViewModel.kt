package com.haeseong.geoquiz.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haeseong.geoquiz.domain.GetQuestionsUseCase
import com.haeseong.geoquiz.domain.Question
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val getQuestionsUseCase: GetQuestionsUseCase,
) : ViewModel() {
    private val _questionsMutableLiveData = MutableLiveData<List<Question>>()
    val questions: LiveData<List<Question>> get() = _questionsMutableLiveData

    private var mCurrentIndex = 0

    fun prev() {
        mCurrentIndex -= 1
        mCurrentIndex += _questionsMutableLiveData.value?.size ?: 0
        mCurrentIndex %= _questionsMutableLiveData.value?.size ?: 1
    }

    fun next() {
        mCurrentIndex += 1
        mCurrentIndex %= _questionsMutableLiveData.value?.size ?: 1
    }

    fun getQuestionContent(): String = questions.value?.get(mCurrentIndex)?.content ?: ""

    fun isAnswerTrue(): Boolean = questions.value?.get(mCurrentIndex)?.answer ?: false

    fun getQuestions() {
        viewModelScope.launch {
            val questions = getQuestionsUseCase.invoke(0, 10)
            if (questions.isNotEmpty()) {
                _questionsMutableLiveData.postValue(questions)
            } else {
                // do nothing
                _questionsMutableLiveData.postValue(
                    listOf(
                        Question(-1, "test", true),
                    )
                )
            }
        }
    }
}