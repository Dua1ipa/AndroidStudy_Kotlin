package com.example.newslist

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "rss")
data class RSSNews(
    @Element(name = "channel")
    val rssChannel: RssChannel
)

@Xml(name = "channel")
data class RssChannel(
    @PropertyElement(name = "title")
    val title: String,

    @Element(name = "item")
    val items: List<NewsItem>? = null
)

@Xml(name = "item")
data class NewsItem(
    @PropertyElement(name = "title")
    val title: String ?= null,

    @PropertyElement(name = "link")
    val link: String ?= null
)