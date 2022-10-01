package com.haeseong.geoquiz

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

/**
 * @see https://stackoverflow.com/a/60205538
 */
class QuizActivity : AppCompatActivity() {

    val mQuestions = listOf(
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true),
    )

    lateinit var mTrueButton: Button
    lateinit var mFalseButton: Button
    lateinit var mNextButton: Button
    lateinit var mQuestionTextView: TextView
    private var mCurrentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        mQuestionTextView = findViewById(R.id.question_text_view)
        updateQuestion()

        mTrueButton = findViewById(R.id.true_button)
        mTrueButton.setOnClickListener {
            Snackbar.make(
                findViewById(R.id.quiz_activity),
                R.string.correct_toast,
                Snackbar.LENGTH_SHORT,
            ).show()
        }
        mFalseButton = findViewById(R.id.false_button)
        mFalseButton.setOnClickListener {
            Snackbar.make(
                findViewById(R.id.quiz_activity),
                R.string.incorrect_toast,
                Snackbar.LENGTH_SHORT,
            ).show()
        }
        mNextButton = findViewById(R.id.next_button)
        mNextButton.setOnClickListener {
            mCurrentIndex += 1
            mCurrentIndex %= mQuestions.size
            updateQuestion()
        }

        updateQuestion()
    }

    private fun updateQuestion() {
        mQuestionTextView.setText(mQuestions[mCurrentIndex].mTextResId)
    }
}