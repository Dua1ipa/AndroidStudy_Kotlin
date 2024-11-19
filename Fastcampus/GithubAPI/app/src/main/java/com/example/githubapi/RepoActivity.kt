package com.example.githubapi

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubapi.adapter.RepoAdapter
import com.example.githubapi.databinding.ActivityRepoBinding
import com.example.githubapi.model.Repo
import com.example.githubapi.network.GithubService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RepoActivity : AppCompatActivity(){
    private lateinit var binding: ActivityRepoBinding
    private lateinit var repoAdapter : RepoAdapter

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRepoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userName = intent.getStringExtra("userName") ?: return
        binding.usernameTextView.text = userName

        repoAdapter = RepoAdapter()

        binding.repoRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = repoAdapter
        }

        listRepo(userName)
    }

    private fun listRepo(userName : String){
        val githubService = retrofit.create(GithubService::class.java)
        githubService.listRepos(userName).enqueue(object : Callback<List<Repo>> {
            override fun onResponse(p0: Call<List<Repo>?>, p1: Response<List<Repo>?>) {
                Log.d("MainActivity", "onResponse: ${p1.body()}")

                repoAdapter.submitList(p1.body())
            }

            override fun onFailure(p0: Call<List<Repo>?>, p1: Throwable) {
                p1.printStackTrace()
            }

        })
    }
}