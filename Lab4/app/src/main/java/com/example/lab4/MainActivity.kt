package com.example.lab4

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private val arrayOfObjectIds = arrayOf(16953,667477,717558,24681,638280,74240,313256,13624,204150,81607,246793,445950,457,14268,50648,451843,188527,318583)

    private val incompleteUri = "https://collectionapi.metmuseum.org/public/collection/v1/objects/"

    private val textView: TextView
        get () = findViewById(R.id.textView)

    private val httpButton: Button
        get () = findViewById(R.id.button_name)

    private val httpImage: ImageView
        get() = findViewById(R.id.imageView)

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        httpImage.setImageResource(R.drawable.ducknft)
        httpButton.setOnClickListener {
            val randomItemFromArray = arrayOfObjectIds.random().toString()
            val completedUri = " $incompleteUri$randomItemFromArray"
            println(completedUri)
            getText(completedUri)
        }
    }

    //Pull the Text from each image Department and Title
    @SuppressLint("SetTextI18n")
    private fun getText(url: String)
    {
        val queue = Volley.newRequestQueue((this))
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val obj = JSONObject(response)
                textView.text = "${obj.get("department")}" +
                    "\n ${obj.get("title")}" +
                        "\n ${obj.get("country")}"
                Toast.makeText(baseContext, "Name: ${obj.get("title")}", Toast.LENGTH_LONG).show()
                getImage(obj.get("primaryImage").toString())
            },
            { textView.text = "That didn't work!" })
        queue.add(stringRequest)
    }

    // Pull the image from the API and upload it instead of default image
    private fun getImage(url: String)
    {
        val queue = Volley.newRequestQueue((this))
        val imageRequest = ImageRequest(url,
            { imageRequest ->
                httpImage.setImageBitmap(imageRequest)
            },
            0, 0, ImageView.ScaleType.CENTER_CROP, null,
            { error ->
                Log.d("RequestError", "Can not pull the image")
            }
        )
        queue.add(imageRequest)
    }
}