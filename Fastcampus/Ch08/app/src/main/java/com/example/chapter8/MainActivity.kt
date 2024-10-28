package com.example.chapter8

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.chapter8.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val imageLoadLuncher =
        registerForActivityResult(ActivityResultContracts.GetMultipleContents()) { uriList ->
            updateImages(uriList)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loadImageButton.setOnClickListener {
            checkPermission()
        }
    }

    private fun checkPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED -> {
                loadImages()
            }

            shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE) -> {
                showPermissionInfoDialog()
            }

            else -> {
                requestReadExternalStorage()
            }
        }

    }

    private fun showPermissionInfoDialog() {
        AlertDialog.Builder(this).apply {
            setMessage("이미지를 가져오기 위해서, 외부 저장소 읽기 권한이 필요합니다.")
            setNegativeButton("취소", null)
            setPositiveButton("동의") { _, _ ->
                requestReadExternalStorage()
            }
        }.show()
    }

    private fun loadImages() {
        imageLoadLuncher.launch("image/*")
    }

    private fun requestReadExternalStorage() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
            REQUEST_READ_EXTERNAL_STORAGE
        )
    }

    companion object {
        const val REQUEST_READ_EXTERNAL_STORAGE = 100
    }

    private fun updateImages(uriList: List<Uri>) {
        Log.i("updateImages", "$uriList")
    }

}