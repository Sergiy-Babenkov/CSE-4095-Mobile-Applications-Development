package com.example.lab2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class SummaryActivity: AppCompatActivity() {

    private val buttonToMainActivity: Button
        get() = findViewById(R.id.button_to_main_activity)

    private val score: TextView
        get() = findViewById(R.id.score_text)

    var score_activity: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary)

        score_activity = findViewById(R.id.score_activity)

        intent?.let {
            val myStr = it.getStringExtra("FROM_MAIN")
            score_activity?.setText("Final Score: $myStr")
        }

        buttonToMainActivity.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                Intent(baseContext,MainActivity::class.java).also{
                        mainActivity ->
                    startActivity(mainActivity)
                }
            }
        })

    }
}