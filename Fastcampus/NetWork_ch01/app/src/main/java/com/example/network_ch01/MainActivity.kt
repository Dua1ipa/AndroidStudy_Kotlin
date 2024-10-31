package com.example.network_ch01

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        NetworkAsyncTask().execute()
    }
}

class NetworkAsyncTask() : AsyncTask<Any?, Any?, Any?>(){
    override fun doInBackground(vararg p0: Any?): Any? {

        val urlString: String = "http://mellowcode.org/json/students/"
        val url: URL = URL(urlString)
        val connection: HttpURLConnection = url.openConnection() as HttpURLConnection

        connection.requestMethod = "GET"
        connection.setRequestProperty("Content-Type", "application/json")

        var buffer = ""
        if (connection.responseCode.equals(HttpURLConnection.HTTP_OK)) {
            val reader = BufferedReader(InputStreamReader(connection.inputStream, "UTF-8"))
            buffer = reader.readLine()
            Log.d("connn", buffer)
        }
        return null
    }
}