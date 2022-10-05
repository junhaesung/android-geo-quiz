package com.haeseong.geoquiz.presentation

import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import com.haeseong.geoquiz.R
import com.haeseong.geoquiz.databinding.ActivityQuizBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * @see https://stackoverflow.com/a/60205538
 */
@AndroidEntryPoint
class QuizActivity : BaseActivity<ActivityQuizBinding>(R.layout.activity_quiz) {
    private val quizViewModel: QuizViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.quizViewModel = quizViewModel
        // 중복호출가능 : 싱글 라이브 이벤트
        quizViewModel.initialize()

        // TODO: view model 로 옮기기
        binding.previousButton.setOnClickListener {
            quizViewModel.prev()
        }
        binding.nextButton.setOnClickListener {
            quizViewModel.next()
        }
        initObserve()
    }

    private fun initObserve() {
        with(quizViewModel) {
            answerSnackBarContentLiveData.observe(this@QuizActivity) {
                Snackbar.make(
                    // 바인딩 객체 접근해야댐
                    binding.quizActivity,
                    it.snackBarContent,
                    Snackbar.LENGTH_SHORT,
                ).show()
            }
        }
    }
}