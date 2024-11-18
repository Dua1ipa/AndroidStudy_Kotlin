package com.example.githubapi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.githubapi.databinding.ActivityRepoBinding

class RepoActivity : AppCompatActivity(){
    private lateinit var binding: ActivityRepoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRepoBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}