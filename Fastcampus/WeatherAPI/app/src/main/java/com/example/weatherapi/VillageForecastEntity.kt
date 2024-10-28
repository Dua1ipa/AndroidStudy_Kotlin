package com.example.weatherapi

import com.google.gson.annotations.SerializedName

data class WeatherEntity(
    @SerializedName("response")
    val response: WeatherResponse
)

data class WeatherResponse(
    @SerializedName("header")
    val header: WeahterHeader,
    @SerializedName("body")
    val body: WeatherBody
)

data class WeahterHeader(
    @SerializedName("resultCode")
    val resultCode: String,
    @SerializedName("resultMsg")
    val resultMsg: String
)

data class WeatherBody(
    @SerializedName("items")
    val items: ForecastEntityList
)

data class ForecastEntityList(
    @SerializedName("item")
    val forcastEntities: List<ForecastEntity>
)

data class ForecastEntity(
    @SerializedName("baseDate")
    val baseDate:String,
    @SerializedName("baseTime")
    val baseTime: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("forecastDate")
    val forecastDate: String,
    @SerializedName("forecastTime")
    val forecastTime: String,
    @SerializedName("forecastValue")
    val forecastValue: String,
    @SerializedName("nx")
    val nx: Int,
    @SerializedName("ny")
    val ny: Int
)