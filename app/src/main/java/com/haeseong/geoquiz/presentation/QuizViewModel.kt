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
    val questionsLiveData: LiveData<List<Question>> get() = _questionsMutableLiveData

    private val _questionIndexMutableLiveData = MutableLiveData<Int>()
    val questionIndexLiveData: LiveData<Int> get() = _questionIndexMutableLiveData

    private val _answerSnackBarContentMutableLiveData = MutableLiveData<AnswerResultEvent>()
    val answerSnackBarContentLiveData: LiveData<AnswerResultEvent> get() = _answerSnackBarContentMutableLiveData

    private var mCurrentIndex = 0

    fun prev() {
        mCurrentIndex -= 1
        mCurrentIndex += _questionsMutableLiveData.value?.size ?: 0
        mCurrentIndex %= _questionsMutableLiveData.value?.size ?: 1

        _questionIndexMutableLiveData.postValue(mCurrentIndex)
    }

    fun next() {
        mCurrentIndex += 1
        mCurrentIndex %= _questionsMutableLiveData.value?.size ?: 1

        _questionIndexMutableLiveData.postValue(mCurrentIndex)
    }

    fun isAnswerTrue(): Boolean = questionsLiveData.value?.get(mCurrentIndex)?.answer ?: false

    fun initialize() {
        viewModelScope.launch {
            val questions = getQuestionsUseCase.invoke(0, 10)
            if (questions.isNotEmpty()) {
                _questionsMutableLiveData.postValue(questions)
                _questionIndexMutableLiveData.postValue(0)
            } else {
                _questionsMutableLiveData.postValue(listOf())
            }
        }
    }

    fun onAnswerButtonClicked(selectedAnswer: Boolean) {
        _answerSnackBarContentMutableLiveData.postValue(
            AnswerResultEvent.from(
                snackBarContent = if (selectedAnswer == isAnswerTrue()) "정답!" else "틀림!"
            )
        )
    }

    data class AnswerResultEvent(
        val snackBarContent: String,
        val createdAt: Long,
    ) {
        companion object {
            fun from(snackBarContent: String): AnswerResultEvent {
                return AnswerResultEvent(
                    snackBarContent = snackBarContent,
                    createdAt = System.currentTimeMillis(),
                )
            }
        }
    }
}