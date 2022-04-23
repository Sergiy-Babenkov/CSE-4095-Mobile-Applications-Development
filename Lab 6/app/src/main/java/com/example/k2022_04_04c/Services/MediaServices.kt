package com.example.k2022_04_04c.Services

import android.app.Service
import android.content.Intent
import android.os.*
import com.example.k2022_03_08_rv.model.MyMediaPlayer

var value: Int = 0
var radioOn: Boolean = false

class MediaServices : Service() {

    private lateinit var serviceLooper: Looper
    private lateinit var serviceHandler: ServiceHandler

    private val myMediaPlayerAudio: MyMediaPlayer = MyMediaPlayer()
    val myMediaPlayerVideo: MyMediaPlayer = MyMediaPlayer()
    private var whatMedia: Int = 0

    private inner class ServiceHandler(looper: Looper) : Handler(looper) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            try {
                Thread.sleep(5000)
            } catch (e: InterruptedException) {
                Thread.currentThread().interrupt()
            }
            stopSelf(msg.arg1)
        }
    }

    override fun onCreate() {
        super.onCreate()

        HandlerThread("Service Args", Process.THREAD_PRIORITY_BACKGROUND).apply {
            start()

            serviceLooper = looper
            serviceHandler = ServiceHandler(serviceLooper)
        }
    }

    fun sendMessagge(message: Message, what: Int) {
        serviceHandler.obtainMessage().also { msg ->
            msg.arg1 = what
            serviceHandler.sendMessage(msg)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)

        serviceHandler.obtainMessage().also { msg ->
            msg.arg1 = startId
            serviceHandler.sendMessage(msg)

        }
        return START_STICKY
    }

    fun SelectMedia(svcs: Int) {
        whatMedia = svcs
    }

    private var binder = LocalBinder()

    override fun onBind(sevice: Intent?): IBinder? {
        return binder
    }

    fun getInt(): Int {
        value = value.plus(1)
        return value
    }

    fun radioToggle(link: String) {

        if (radioOn) {
            myMediaPlayerAudio.pause()
        } else {
            myMediaPlayerAudio.setUpRadio(link)
            myMediaPlayerAudio.prepareAndPlayStation(link)
        }

        radioOn = !radioOn
    }

    inner class LocalBinder : Binder() {
        fun getInstance(): MediaServices = this@MediaServices
    }
}