package com.example.orderpage

import android.content.Context
import com.google.gson.Gson
import java.io.IOException

fun <T>Context.readData(fileName: String, classT : Class<T>): T? {

    return try {
        val inputStream = this.resources.assets.open(fileName)  //스트림 열기 -> fileName 데이터 읽기
        val buffer = ByteArray(inputStream.available())  //버퍼 생성 -> 데이터 넣기
        inputStream.read(buffer)  //버퍼 읽기
        inputStream.close() //스트림 닫기

        val gson = Gson()  //Gson 객체를 생성 (구글에서 만든 JSON 파싱 라이브러리)
        gson.fromJson(String(buffer), classT)  //buffer를 String 형식으로 변환 -> JSON 형식의 문자열로 -> 제네릭 T클래스 타입의 객체로 변환
    } catch (e: IOException) {
        e.printStackTrace()
        null
    }

}