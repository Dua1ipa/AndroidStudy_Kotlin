package com.example.weatherapi

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.weatherapi.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(WeatherService::class.java)
        service.getVillageForecast(
            serviceKey = "X/NC6EMLXsFWpAKeTLD9RbbRa6ijogzDhkGI/xq5lcUJ1HOAiUzJETAxXOnrsHgNX6GKVnvCOm5u2R8TBPxDqQ==",
            baseDate = "20241028",
            baseTime = "1700",
            nx = 5,
            ny = 127
        ).enqueue(object: Callback<WeatherEntity>{
            override fun onResponse(call: Call<WeatherEntity>, response: Response<WeatherEntity>) {
                val forecastDateTimeMap = mutableMapOf<String, ForecastEntity>()

                val forecastList = response.body()?.response?.body?.items?.forcastEntities.orEmpty()
                for (forecast in forecastList){
                    Log.e("Forecast", forecast.toString())
                    if (forecastDateTimeMap["${forecast.forecastDate}/${forecast.forecastTime}"] == null){
                        
                    }
                    "${forecast.forecastDate}/${forecast.forecastTime}"
                }
            }

            override fun onFailure(call: Call<WeatherEntity>, t: Throwable) {
                t.printStackTrace()
            }

        })

    }
}