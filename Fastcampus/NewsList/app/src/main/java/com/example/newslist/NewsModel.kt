package com.example.newslist

data class NewsModel(
    val title: String,
    val link: String,
    var imageURL: String? = null
)

fun List<NewsItem>.transform(): List<NewsModel> {
    return this.map {
        NewsModel(
            title = it.title ?: "",
            link = it.link ?: "",
            imageURL = null
        )
    }
}
