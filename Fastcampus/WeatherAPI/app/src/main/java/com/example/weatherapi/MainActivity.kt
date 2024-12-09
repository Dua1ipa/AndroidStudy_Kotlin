package com.example.weatherapi

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapi.databinding.ActivityMainBinding
import com.example.weatherapi.databinding.ItemForecastBinding
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }
    private lateinit var binding: ActivityMainBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val locationPermissionRequest = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {  // ACCESS_COARSE_LOCATION 권한이 허용된 경우
                checkLocation()
            } else -> { //권한이 없으면
                Toast.makeText(this, "위치 권한이 필요합니다.", Toast.LENGTH_SHORT).show()
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {  //설정 화면으로 이동
                    data = Uri.fromParts("package", packageName, null)
                }
                startActivity(intent)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        locationPermissionRequest.launch(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION))

        // 슬라이드 되는 부분 //
        binding.childForecastLinearLayout.apply {
            val itemView = ItemForecastBinding.inflate(layoutInflater)
            itemView.timeTextView.text = "1000"
            itemView.weatherTextView.text = "맑음"
            itemView.temperatureTextView.text = "10"
            addView(itemView.root)
        }
    }

    private fun checkLocation() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //권한이 없으면 실행
            locationPermissionRequest.launch(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION))
            return
        }
        fusedLocationClient.lastLocation
            .addOnSuccessListener {  //위치 정보를 가져오면 실행
                //레트로핏
                val retrofit = Retrofit.Builder()
                    .baseUrl("http://apis.data.go.kr/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                val service = retrofit.create(WeatherService::class.java)
                service.getVillageForecast(
                    serviceKey = "BQs94jz33Lnm8YCDYFrEZARM/dfFb6s+XHewtMnQFTblEn6rRQtSuHTtjli1gvQBqDzoVRM7ei/7RDx+cdR3Vg==",
                    baseDate = "20241209",
                    baseTime = "1100",
                    nx = 55,
                    ny = 127
                ).enqueue(object: Callback<WeatherEntity> {
                    override fun onResponse(p0: Call<WeatherEntity>, p1: Response<WeatherEntity>) {
                        val itemList = p1.body()?.response?.body?.items?.item.orEmpty()

                        for (item in itemList) {
                            Log.e(TAG, item.toString())
                        }
                    }

                    override fun onFailure(p0: Call<WeatherEntity>, p1: Throwable) {

                    }

                })

//                val baseDateTime = BaseDateTime.getBaseDateTime()
//                val converter = GeoPointConverter()
//                val point = converter.convert(lat = it.latitude, lon = it.longitude)
            }
    }
}