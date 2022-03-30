package com.example.k2022_03_08_rv

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.k2022_03_08_rv.model.RadioStations


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private var radioStations = RadioStations()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recycleview)
        recyclerView.adapter = RadioAdapter(radioStations)
        recyclerView.layoutManager = LinearLayoutManager(baseContext)
    }
    private fun onPrepared(mediaPlayer: MediaPlayer){
        mediaPlayer.start()
    }
    fun setAndPrepareRadioLink(url: String, mediaPlayer: MediaPlayer)
    {
        mediaPlayer.reset()
        mediaPlayer.setDataSource(url)
        mediaPlayer.setOnPreparedListener(this::onPrepared)
        mediaPlayer.prepareAsync()
    }
}