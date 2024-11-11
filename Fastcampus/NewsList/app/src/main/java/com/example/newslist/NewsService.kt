package com.example.newslist

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("rss?hl=ko&gl=KR&ceid=KR:ko")
    fun mainFeed(): Call<RSSNews>

    @GET("rss/topics/CAAqIQgKIhtDQkFTRGdvSUwyMHZNRFZ4ZERBU0FtdHZLQUFQAQ?hl=ko&gl=KR&ceid=KR%3Ako")
    fun politicsNews(): Call<RSSNews>

    @GET("rss/topics/CAAqIggKIhxDQkFTRHdvSkwyMHZNR2RtY0hNekVnSnJieWdBUAE?hl=ko&gl=KR&ceid=KR%3Ako")
    fun economicsNews(): Call<RSSNews>

    @GET("rss/topics/CAAqIggKIhxDQkFTRHdvSkwyMHZNREU1ZHpab0VnSnJieWdBUAE?hl=ko&gl=KR&ceid=KR%3Ako")
    fun sportsNews(): Call<RSSNews>

    @GET("rss/topics/CAAqIQgKIhtDQkFTRGdvSUwyMHZNRE41TXprU0FtdHZLQUFQAQ?hl=ko&gl=KR&ceid=KR%3Ako")
    fun itNews(): Call<RSSNews>

    @GET("rss/topics/CAAqIQgKIhtDQkFTRGdvSUwyMHZNRGs0ZDNJU0FtdHZLQUFQAQ?hl=ko&gl=KR&ceid=KR%3Ako")
    fun societyNews(): Call<RSSNews>

    @GET("rss/search?&hl=ko&gl=KR&ceid=KR%3Ako")
    fun search(@Query("q") query: String): Call<RSSNews>
}