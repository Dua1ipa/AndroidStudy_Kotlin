package com.example.navermap

import android.app.Application
import android.content.Context

class MyApp : Application() {
    companion object {
        lateinit var applicationContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        MyApp.applicationContext = applicationContext
    }

}