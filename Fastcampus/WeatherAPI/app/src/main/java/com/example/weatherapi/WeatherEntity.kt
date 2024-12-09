package com.example.weatherapi

import com.google.gson.annotations.SerializedName

data class WeatherEntity (
    @SerializedName("response")
    val response: WeatherResponse
)

data class WeatherResponse (
    @SerializedName("header")
    val header: WeatherHeader,

    @SerializedName("body")
    val body: WeatherBody
)

data class WeatherHeader (
    @SerializedName("resultCode")
    val resultCode: String,

    @SerializedName("resultMsg")
    val resultMsg: String
)

data class WeatherBody (
    @SerializedName("items")
    val items: ItemsList
)

data class ItemsList (
    @SerializedName("item")
    val item: List<Item>
)

data class Item (
    @SerializedName("baseDate")
    val baseDate: String,
    @SerializedName("baseTime")
    val baseTime: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("fcstTime")
    val forecastTime: String,
    @SerializedName("fcstValue")
    val forecastValue: String,
    @SerializedName("nx")
    val nx: Int,
    @SerializedName("ny")
    val ny: Int
)