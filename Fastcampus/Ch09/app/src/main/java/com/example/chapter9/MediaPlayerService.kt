package com.example.chapter9

import android.app.Service
import android.content.Intent
import android.os.IBinder

class MediaPlayerService : Service() {
    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}