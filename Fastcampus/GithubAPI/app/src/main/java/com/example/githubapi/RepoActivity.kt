package com.example.githubapi

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    private lateinit var linearLayoutManager: LinearLayoutManager

    private var page = 0
    private var hasMore : Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRepoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userName = intent.getStringExtra("userName") ?: return
        binding.usernameTextView.text = userName

        repoAdapter = RepoAdapter{
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.htmlUrl))  //자체 인터넷 뷰
            startActivity(intent)
        }
        linearLayoutManager = LinearLayoutManager(this@RepoActivity)

        binding.repoRecyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = repoAdapter
        }

        // 스크롤 하면 API 정보 미리 로딩 //
        binding.repoRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val totalCount = linearLayoutManager.itemCount
                val lastVisiblePosition =linearLayoutManager.findLastCompletelyVisibleItemPosition()  //마지막 아이템 위치 찾기

                if(lastVisiblePosition >= (totalCount - 1) && hasMore){
                    page += 1
                    listRepo(userName, page)
                }
            }
        })

        listRepo(userName, 0)
    }

    private fun listRepo(userName : String, page : Int){
        val githubService = APIClient(getString(R.string.github_api_key)).retrofit.create(GithubService::class.java)
        githubService.listRepos(userName, page).enqueue(object : Callback<List<Repo>> {
            override fun onResponse(p0: Call<List<Repo>?>, p1: Response<List<Repo>?>) {
                Log.d("MainActivity", "onResponse: ${p1.body()}")

                hasMore = p1.body()?.count() == 30

                repoAdapter.submitList(repoAdapter.currentList + p1.body().orEmpty())
            }

            override fun onFailure(p0: Call<List<Repo>?>, p1: Throwable) {
                p1.printStackTrace()
            }

        })
    }
}