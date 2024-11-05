package com.example.voicerecoder

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.voicerecoder.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    companion object {
        private const val REQUEST_RECORD_AUDIO_CODE = 200
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.playButton.setOnClickListener {

        }
        binding.recordButton.setOnClickListener {
            when {
                //권한이 허용 되었는가?
                ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.RECORD_AUDIO
                ) == PackageManager.PERMISSION_GRANTED -> {
                    //실제로 녹음을 시작하면 됨
                }

                // 사용자에게 권한 요청 설명이 필요한가?
                ActivityCompat.shouldShowRequestPermissionRationale(
                    this, android.Manifest.permission.RECORD_AUDIO
                ) -> {
                    showPermissionRationalDialog()
                }

                // 권한이 없고 권한 요청 설명이 필요한 경우
                else -> {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(android.Manifest.permission.RECORD_AUDIO),
                        REQUEST_RECORD_AUDIO_CODE
                    )
                }
            }
        }
        binding.stopButton.setOnClickListener {

        }
    }

    // 권한 요청 설명 다이얼로그 함수 //
    private fun showPermissionRationalDialog() {
        AlertDialog.Builder(this)
            .setTitle("녹음 권한")
            .setPositiveButton("권한 허용하기") { _, _ ->
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.RECORD_AUDIO),
                    REQUEST_RECORD_AUDIO_CODE
                )
            }
            .setNegativeButton("권한 취소하기") { dialogInterface, _ ->
                dialogInterface.cancel()
            }.show()
    }

    // 권한 받은 결과 함수 // 
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
        deviceId: Int
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults, deviceId)
    }

}