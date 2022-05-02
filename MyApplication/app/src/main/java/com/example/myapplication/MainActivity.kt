package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.models.Question
import com.example.myapplication.models.Score
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var b_answer1: Button
    private lateinit var b_answer2: Button
    private lateinit var b_answer3: Button
    private lateinit var b_answer4: Button

    private val doneButton: Button
        get() = findViewById(R.id.done_button)

    private var getItem = mutableListOf<Question>()

    private var currentQ: Int = 0

    private var scoreText: TextView? = null
    private var score: Int? = 0
    private val total_score: Score = Score()

    private lateinit var getDataButton: Button
    private lateinit var basicQuestionView: TextView
    private lateinit var imageView: ImageView

    private val urlJSON = "http://192.168.181.127:8080/questions"

    private val arrayOfObjectIds =
        arrayOf("UCONN.jpg", "C8.jpg", "NFT.jpg", "F1.jpg", "stamford_harbor.jpg")

    private val incompleteUri = "http://192.168.181.127:8080/static/"


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        b_answer1 = findViewById(R.id.answer1)
        b_answer2 = findViewById(R.id.answer2)
        b_answer3 = findViewById(R.id.answer3)
        b_answer4 = findViewById(R.id.answer4)

        getDataButton = findViewById(R.id.get_data_button)
        basicQuestionView = findViewById(R.id.basic_question_view)
        imageView = findViewById(R.id.imageView)

        b_answer1.visibility = View.GONE
        b_answer2.visibility = View.GONE
        b_answer3.visibility = View.GONE
        b_answer4.visibility = View.GONE
        doneButton.visibility = View.GONE

        imageView.setImageResource(R.drawable.akarinft)

        scoreText = findViewById((R.id.score_text))
        scoreText?.text = "Score: $score"

        getDataButton.setOnClickListener {
            getQuestions()
            getImage()
            b_answer1.visibility = View.VISIBLE
            b_answer2.visibility = View.VISIBLE
            b_answer3.visibility = View.VISIBLE
            b_answer4.visibility = View.VISIBLE
            doneButton.visibility = View.VISIBLE
        }

        b_answer1.setOnClickListener {
            if (getItem[currentQ].getAnswer1() == getItem[currentQ].getCorrect()) {
                score = total_score.inc()
                scoreText?.text = "Score: $score"
                Toast.makeText(baseContext, "Correct", Toast.LENGTH_SHORT).show()
            } else {
                score = total_score.dec()
                scoreText?.text = "Score: $score"
                Toast.makeText(baseContext, "Incorrect", Toast.LENGTH_SHORT).show()
            }
        }

        b_answer2.setOnClickListener {
            if (getItem[currentQ].getAnswer2() == getItem[currentQ].getCorrect()) {
                score = total_score.inc()
                scoreText?.text = "Score: $score"
                Toast.makeText(baseContext, "Correct", Toast.LENGTH_SHORT).show()
            } else {
                score = total_score.dec()
                scoreText?.text = "Score: $score"
                Toast.makeText(baseContext, "Incorrect", Toast.LENGTH_SHORT).show()
            }
        }

        b_answer3.setOnClickListener {
            if (getItem[currentQ].getAnswer3() == getItem[currentQ].getCorrect()) {
                score = total_score.inc()
                scoreText?.text = "Score: $score"
                Toast.makeText(baseContext, "Correct", Toast.LENGTH_SHORT).show()
            } else {
                score = total_score.dec()
                scoreText?.text = "Score: $score"
                Toast.makeText(baseContext, "Incorrect", Toast.LENGTH_SHORT).show()
            }
        }

        b_answer4.setOnClickListener {
            if (getItem[currentQ].getAnswer4() == getItem[currentQ].getCorrect()) {
                score = total_score.inc()
                scoreText?.text = "Score: $score"
                Toast.makeText(baseContext, "Correct", Toast.LENGTH_SHORT).show()
            } else {
                score = total_score.dec()
                scoreText?.text = "Score: $score"
                Toast.makeText(baseContext, "Incorrect", Toast.LENGTH_SHORT).show()
            }
        }

        doneButton.setOnClickListener {
            Intent(baseContext, SummaryActivity::class.java).also { summaryActivity ->
                summaryActivity.putExtra("FROM_MAIN", score.toString())
                startActivity(summaryActivity)
            }
            total_score.reset()
            Log.i("SB", "Done is selected, moving to Summary Activity")
        }
    }

    @SuppressLint("SetTextI18n")
    private fun getQuestions() {
        getItem = ArrayList()
        val queue = Volley.newRequestQueue(this)
        val stringReq = StringRequest(Request.Method.GET, urlJSON,
            { response ->

                val strResp = response.toString()
                val jsonObj = JSONObject(strResp)
                val jsonArray: JSONArray = jsonObj.getJSONArray("questions")
                for (i in 0 until jsonArray.length()) {
                    val question: JSONObject = jsonArray.getJSONObject(i)
                    val questionString = question.getString("question")
                    val answer1String = question.getString("answer1")
                    val answer2String = question.getString("answer2")
                    val answer3String = question.getString("answer3")
                    val answer4String = question.getString("answer4")
                    val correctString = question.getString("correct")

                    getItem.add(
                        Question(
                            questionString,
                            answer1String,
                            answer2String,
                            answer3String,
                            answer4String,
                            correctString
                        )
                    )
                    Log.e("Test", getItem.toString())

                    getItem.shuffle()

                    basicQuestionView.text = getItem[currentQ].getQuestion()
                    b_answer1.text = getItem[currentQ].getAnswer1()
                    b_answer2.text = getItem[currentQ].getAnswer2()
                    b_answer3.text = getItem[currentQ].getAnswer3()
                    b_answer4.text = getItem[currentQ].getAnswer4()
                }
            },
            { basicQuestionView.text = "Error" })
        queue.add(stringReq)
    }

    @SuppressLint("SetTextI18n")
    private fun getImage() {
        val randomItemFromArray = arrayOfObjectIds.random().toString()
        val completedUri = " $incompleteUri$randomItemFromArray"
        val queue = Volley.newRequestQueue(this)
        val imageRequest = ImageRequest(
            completedUri,
            { response: Bitmap ->
                imageView.setImageBitmap(response)
            },
            0, 0,
            ImageView.ScaleType.CENTER_CROP, Bitmap.Config.RGB_565,

            { error -> basicQuestionView.text = "Error: $error" })
        queue.add(imageRequest)
    }
}
