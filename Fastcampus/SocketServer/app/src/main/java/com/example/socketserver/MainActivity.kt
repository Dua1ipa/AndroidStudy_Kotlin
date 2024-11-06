package com.example.socketserver

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.socketserver.databinding.ActivityMainBinding
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okio.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val client = OkHttpClient()
        val request: Request = Request.Builder()
            .url("http://10.0.2.2:8080")
            .build()

        val callback = object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("Client", e.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                if(response.isSuccessful)
                    Log.d("Client", "${response.body?.string()}")
            }

        }
        client.newCall(request).enqueue(callback)

//        Thread() {
//            try {
//                val socket = Socket("10.0.2.2", 8080)
//                val printer = PrintWriter(socket.getOutputStream())
//                val reader = BufferedReader(InputStreamReader(socket.getInputStream()))
//
//                printer.println("Get / HTTP/1.1")
//                printer.println("Host: 127.0.0.1:8080")
//                printer.println("User-Agent: android")
//                printer.println("\r\n")
//                printer.flush()
//
//                var input: String? = "_"
//                while (input != null) {
//                    input = reader.readLine()
//                }
//                reader.close()
//                printer.close()
//                socket.close()
//            }catch (e:Exception){
//                Log.e("Client", e.toString())
//            }
//        }.start()

    }

}