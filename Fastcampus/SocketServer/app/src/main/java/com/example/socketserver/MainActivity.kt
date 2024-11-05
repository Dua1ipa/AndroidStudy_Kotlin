package com.example.socketserver

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.socketserver.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.ServerSocket

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 스레드 //
        Thread {
            val port = 8080
            val server = ServerSocket(port)

            val socket = server.accept()

            val reader = BufferedReader(InputStreamReader(socket.getInputStream()))  // 클라이언트부분 <- 서버
            val printer = PrintWriter(socket.getOutputStream()) // 서버부분 -> 클라이언트

            // 클라이언트 부분 //
            var input: String? = "_"
            while (input != null && input != "") {
                input = reader.readLine()
            }
            Log.d("Server", "Read Data: $input")

            // 서버 부분 //
            printer.println("HTTP/1.1 200 OK")
            printer.println("Content-Type: text/html\r\n")

            printer.println("<html><body><h1>Hello World</h1></body></html>")
            printer.println("\r\n")
            printer.flush()
            printer.close()

            // 클라이언트 닫기 //
            reader.close()

            // 소켓 닫기 //
            socket.close()
        }.start()

    }

}