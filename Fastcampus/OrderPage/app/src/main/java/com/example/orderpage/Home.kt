package com.example.orderpage

data class Home(
    val user: User,
    val appBarImage: String,
    val banner: Banner
)

data class User(
    val nickName: String,
    val starCount: Int,
    val totalCount: Int
)

data class Banner(
    val image: String,
    val contentDescription: String
)