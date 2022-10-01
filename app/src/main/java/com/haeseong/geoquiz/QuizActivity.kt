package com.haeseong.geoquiz

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar

/**
 * @see https://stackoverflow.com/a/60205538
 */
class QuizActivity : AppCompatActivity() {
    private lateinit var mTrueButton: Button
    private lateinit var mFalseButton: Button
    private lateinit var mPreviousButton: Button
    private lateinit var mNextButton: Button
    private lateinit var mQuestionTextView: TextView

    private val mQuizViewModel: QuizViewModel by lazy {
        ViewModelProvider(this).get(QuizViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

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
            mQuizViewModel.prev()
            updateQuestion()
        }
        mNextButton = findViewById(R.id.next_button)
        mNextButton.setOnClickListener {
            mQuizViewModel.next()
            updateQuestion()
        }

        updateQuestion()
    }

    private fun updateQuestion() {
        mQuestionTextView.setText(mQuizViewModel.getQuestionResourceId())
    }

    private fun checkAnswer(userPressedTrue: Boolean) {
        val messageResId = if (userPressedTrue == mQuizViewModel.isAnswerTrue()) {
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
}