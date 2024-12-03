package com.example.videoplayer

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.videoplayer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var videoAdapter: VideoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        videoAdapter = VideoAdapter()
        binding.videoListRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = videoAdapter
        }
    }

}