package com.example.lab2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lab2.controllers.NextQuestion
import com.example.lab2.model.AllQuestions
import com.example.lab2.model.Score

class MainActivity : AppCompatActivity() {

    var trueButton: Button? = null
    var falseButton: Button? = null
    var nextButton: Button? = null
    var questionText: TextView? = null
    var scoreText: TextView? = null
    var score: Int? = 0
    val total_score: Score = Score()
    var questions: AllQuestions = AllQuestions()
    val nextQuestion: NextQuestion = NextQuestion()

    private val doneButton: Button
        get() = findViewById(R.id.done_button)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.button_to_main_activity)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)

        questionText = findViewById(R.id.score_activity)

        scoreText = findViewById((R.id.score_text))
        scoreText?.setText("Score: $score")

        trueButton?.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                if (questions.allQuestions[nextQuestion.getIndex()].isTrue){
                    score = total_score.inc()
                    scoreText?.setText("Score: $score")
                    Toast.makeText(baseContext,"Correct", Toast.LENGTH_SHORT).show()
                    Log.i("SB","Correct is selected")
                }
                else {
                    score = total_score.dec()
                    scoreText?.setText("Score: $score")
                    Toast.makeText(baseContext, "Incorrect", Toast.LENGTH_SHORT).show()
                    Log.i("SB","Incorrect is selected")
                }
                val nextQuestionIndex = nextQuestion.linearNextQuestion()
                questionText?.setText(nextQuestionIndex )
                Log.i("SB","True is selected, going to next question")
            }
        })

        falseButton?.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                if (questions.allQuestions[nextQuestion.getIndex()].isTrue){
                    score = total_score.dec()
                    scoreText?.setText("Score: $score")
                    Toast.makeText(baseContext, "Incorrect", Toast.LENGTH_SHORT).show()
                }
                else {
                    score = total_score.inc()
                    scoreText?.setText("Score: $score")
                    Toast.makeText(baseContext, "Correct", Toast.LENGTH_SHORT).show()
                }
                val nextQuestionIndex = nextQuestion.linearNextQuestion()
                questionText?.setText(nextQuestionIndex)
                Log.i("SB","False is selected, going to next question")
            }
        })

        nextButton?.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                score = total_score.skip()
                scoreText?.setText("Score: $score")
                val nextQuestionIndex = nextQuestion.linearNextQuestion()
                questionText?.setText(nextQuestionIndex)
                Toast.makeText(baseContext, "Clicked Next", Toast.LENGTH_SHORT).show()
                Log.i("SB","Next is selected, skipping question")

            }
        })

        doneButton.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?) {
                Intent(baseContext, SummaryActivity::class.java).also { summaryActivity ->
                    summaryActivity.putExtra("FROM_MAIN", score.toString())
                    startActivity(summaryActivity)
                }
                total_score.reset()
                Log.i("SB","Done is selected, moving to Summary Activity")
            }
        })

    }
}