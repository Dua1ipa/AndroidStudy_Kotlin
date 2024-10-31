package com.example.youtube

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {
    @GET("youtube/list/")
    fun getYoutubeItemList(): Call<ArrayList<YoutubeItem>>
}

class YoutubeItem(
    val id: Int, val title: String, val content: String, val video: String, val thumbnail: String
)