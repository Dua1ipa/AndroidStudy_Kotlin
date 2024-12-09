package com.example.weatherapi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

// https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst?serviceKey=[]&pageNo=1&numOfRows=1000&dataType=JSON&base_date=[]&base_time=0[]&nx=[]&ny=[]

interface WeatherService {
    @GET("/1360000/VilageFcstInfoService_2.0/getVilageFcst?&pageNo=1&numOfRows=400&dataType=JSON")
    fun getVillageForecast(
        @Query("serviceKey") serviceKey: String,  //키
        @Query("base_date") baseDate: String,  //날짜
        @Query("base_time") baseTime: String,  //시간
        @Query("nx") nx: Int,  //위도
        @Query("ny") ny: Int,  //경도
    ): Call <WeatherEntity>
}