package com.example.orderpage

import android.content.Context
import com.google.gson.Gson
import java.io.IOException

fun Context.readData(): Home? {

    return try {
        val inputStream = this.resources.assets.open("home.json")  //스트림 열기 -> 데이터 읽기
        val buffer = ByteArray(inputStream.available())  //버퍼 생성 -> 데이터 넣기
        inputStream.read(buffer)  //버퍼 읽기
        inputStream.close() //스트림 닫기

        val gson = Gson()  //Gson 객체를 생성 (구글에서 만든 JSON 파싱 라이브러리)
        gson.fromJson(String(buffer), Home::class.java)  //buffer를 String 형식으로 변환 -> JSON 형식의 문자열로 -> Home 클래스 타입의 객체로 변환
    } catch (e: IOException) {
        e.printStackTrace()
        null
    }

}