package com.example.githubapi

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubapi.adapter.UserAdapter
import com.example.githubapi.databinding.ActivityMainBinding
import com.example.githubapi.model.Repo
import com.example.githubapi.model.UserDTO
import com.example.githubapi.network.GithubService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var userAdapter: UserAdapter
    private lateinit var githubService: GithubService

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val handler = Handler(Looper.getMainLooper())
    private var searchFor : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        githubService = retrofit.create(GithubService::class.java)
        githubService.listRepos("square").enqueue(object : Callback<List<Repo>> {
            override fun onResponse(p0: Call<List<Repo>?>, p1: Response<List<Repo>?>) {
                Log.d("MainActivity", "onResponse: ${p1.body()}")
            }

            override fun onFailure(p0: Call<List<Repo>?>, p1: Throwable) {
                p1.printStackTrace()
            }

        })

        userAdapter = UserAdapter{
            val intent = Intent(this@MainActivity, RepoActivity::class.java)
            intent.putExtra("userName", it.username)
            startActivity(intent)
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = userAdapter
        }

        val runnable = Runnable{
            searchUser()
        }

        binding.searchEditText.addTextChangedListener {
            searchFor = it.toString()
            handler.removeCallbacks(runnable)
            handler.postDelayed(
                runnable,
                300
            )
        }
        
    }

    private fun searchUser(){
        githubService.searchUsers(searchFor).enqueue(object : Callback<UserDTO> {
            override fun onResponse(p0: Call<UserDTO>, p1: Response<UserDTO>) {
                userAdapter.submitList(p1.body()?.items)
            }

            override fun onFailure(p0: Call<UserDTO>, p1: Throwable) {
                p1.printStackTrace()
                Toast.makeText(this@MainActivity, "에러가 발생했습니다.", Toast.LENGTH_SHORT).show()
            }
        })
    }
}