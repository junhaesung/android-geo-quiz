package com.haeseong.geoquiz

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

/**
 * @see https://stackoverflow.com/a/60205538
 */
class QuizActivity : AppCompatActivity() {
    lateinit var mTrueButton: Button
    lateinit var mFalseButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
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
    }
}