package com.haeseong.geoquiz.presentation

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.haeseong.geoquiz.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking

/**
 * @see https://stackoverflow.com/a/60205538
 */
@AndroidEntryPoint
class QuizActivity : AppCompatActivity() {
    private lateinit var mTrueButton: Button
    private lateinit var mFalseButton: Button
    private lateinit var mPreviousButton: ImageButton
    private lateinit var mNextButton: ImageButton
    private lateinit var mQuestionTextView: TextView

    private val quizViewModel: QuizViewModel by viewModels()

//    private val mQuizViewModel: QuizViewModel by lazy {
//        ViewModelProvider(this).get(QuizViewModel::class.java)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        // viewModel 데이터 초기화 - 여기서 하는거 이상함
        quizViewModel.getQuestions()

        // 화면 그리기 및 동작
        mQuestionTextView = findViewById(R.id.question_text_view)
        updateQuestion()

        mTrueButton = findViewById(R.id.true_button)
        mTrueButton.setOnClickListener {
            checkAnswer(true)
        }
        mFalseButton = findViewById(R.id.false_button)
        mFalseButton.setOnClickListener {
            checkAnswer(false)
        }
        mPreviousButton = findViewById(R.id.previous_button)
        mPreviousButton.setOnClickListener {
            quizViewModel.prev()
            updateQuestion()
        }
        mNextButton = findViewById(R.id.next_button)
        mNextButton.setOnClickListener {
            quizViewModel.next()
            updateQuestion()
        }

        updateQuestion()

        initObserve()
    }

    private fun updateQuestion() {
        mQuestionTextView.text = quizViewModel.getQuestionContent()
    }

    private fun checkAnswer(userPressedTrue: Boolean) {
        val messageResId = if (userPressedTrue == quizViewModel.isAnswerTrue()) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }
        Snackbar.make(
            findViewById(R.id.quiz_activity),
            messageResId,
            Snackbar.LENGTH_SHORT,
        ).show()
    }

    private fun initObserve() {
        quizViewModel.questions.observe(this) {
            println("initObserve: $it")
        }

        with(quizViewModel) {
//            tempResult.observe(this@QuizActivity) {
//                // 가져온 it을 화면에 뿌려주는 작업
//            }
        }
    }
}