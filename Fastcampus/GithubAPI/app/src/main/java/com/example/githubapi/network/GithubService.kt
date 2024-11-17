package com.example.githubapi.network

import com.example.githubapi.model.Repo
import com.example.githubapi.model.UserDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {
    @Headers("Authorization: Bearer ")
    @GET("users/{username}/repos")
    fun listRepos(@Path("username") username: String): Call<List<Repo>>

    @GET("search/users")
    fun searchUsers(@Query("q") query: String): Call<UserDTO>
}