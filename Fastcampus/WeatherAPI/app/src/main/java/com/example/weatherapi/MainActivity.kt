package com.example.weatherapi

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapi.databinding.ActivityMainBinding
import com.example.weatherapi.databinding.ItemForecastBinding
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 레트로 핏 //
        val retrofit = Retrofit.Builder()
            .baseUrl("http://apis.data.go.kr/")

        // 슬라이드 되는 부분 //
        binding.childForecastLinearLayout.apply {
            val itemView = ItemForecastBinding.inflate(layoutInflater)
            itemView.timeTextView.text = "1000"
            itemView.weatherTextView.text = "맑음"
            itemView.temperatureTextView.text = "10"
            addView(itemView.root)
        }
    }

}