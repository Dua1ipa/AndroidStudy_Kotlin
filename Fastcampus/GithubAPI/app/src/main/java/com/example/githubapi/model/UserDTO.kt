package com.example.githubapi.model

import com.google.gson.annotations.SerializedName

data class UserDTO(
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("items")
    val items: List<User>
)