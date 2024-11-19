package com.example.githubapi

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIClient {
    private const val BASE_URL = "https://api.github.com"

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor{
            val request = it.request()
                .newBuilder()
                .addHeader("Authorization", "Bearer ${R.string.GithubAPI_KEY}")
                .build()
            it.proceed(request)
        }
        .build()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}