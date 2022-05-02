package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SummaryActivity : AppCompatActivity() {

    private val buttonToMainActivity: Button
        get() = findViewById(R.id.button_to_main_activity)

    private val score: TextView
        get() = findViewById(R.id.score_text)

    private var score_activity: TextView? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary)

        score_activity = findViewById(R.id.score_activity)

        intent?.let {
            val myStr = it.getStringExtra("FROM_MAIN")
            score_activity?.setText("Final Score: $myStr")
        }

        buttonToMainActivity.setOnClickListener {
            Intent(baseContext, MainActivity::class.java).also { mainActivity ->
                startActivity(mainActivity)
            }
        }
    }
}