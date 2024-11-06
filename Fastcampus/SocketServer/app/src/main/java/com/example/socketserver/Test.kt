package com.example.socketserver

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.ServerSocket

fun main() {
    // 스레드 //
    Thread {
        val port = 8080
        val server = ServerSocket(port)

        while (true) {
            val socket = server.accept()

            val reader = BufferedReader(InputStreamReader(socket.getInputStream()))  // 클라이언트부분 <- 서버
            val printer = PrintWriter(socket.getOutputStream()) // 서버부분 -> 클라이언트

            // 클라이언트 부분 //
            var input: String? = "_"
            while (input != null && input != "") {
                input = reader.readLine()
            }
            println("Read Data: $input")

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
        }

    }.start()
}