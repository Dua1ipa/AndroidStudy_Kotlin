package com.example.chapter9

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.chapter9.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pauseButton.setOnClickListener { mediaPause() }
        binding.playButton.setOnClickListener { mediaPlay() }
        binding.stopButton.setOnClickListener { mediaStop() }

    }

    private fun mediaPlay(){
        if(mediaPlayer == null){
            mediaPlayer = MediaPlayer.create(this, R.raw.sound).apply {

            }
            mediaPlayer?.start()
        }
    }

    private fun mediaPause(){
        mediaPlayer?.pause()
    }

    private fun mediaStop(){
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }

}