package com.example.videoplayer

import com.google.gson.annotations.SerializedName

data class VideoList(
    val videos: List<Video>
)

data class Video(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("sources")
    val thumbnailUrl: String,
    @SerializedName("channelName")
    val channelName: String,
    @SerializedName("viewCount")
    val viewCount: String,
    @SerializedName("dataText")
    val dataText: String,
    @SerializedName("channelThumb")
    val channelThumb: String,
    @SerializedName("thumb")
    val videoThumb: String
)
