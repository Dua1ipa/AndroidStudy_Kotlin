package fastcampus.part1.chapter6

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fastcampus.part1.chapter6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.playButton.setOnClickListener {
            start()
        }

        binding.pauseButton.setOnClickListener {
            pause()
        }

        binding.stopButton.setOnClickListener {
            stop()
        }
    }

    private fun start(){

    }

    private fun pause(){

    }

    private fun stop(){

    }

}