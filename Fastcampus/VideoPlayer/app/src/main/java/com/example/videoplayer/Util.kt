package com.example.videoplayer

import android.content.Context
import com.google.gson.Gson
import java.io.IOException

fun <T> Context.readData(fileName: String, classT: Class<T>): T? {
    return try {
        val inputStream = this.resources.assets.open(fileName)
        val byteArray = ByteArray(inputStream.available())
        inputStream.read(byteArray)
        inputStream.close()

        Gson().fromJson(inputStream.toString(), classT)

    } catch (ioException: IOException) {
        null
    }

}