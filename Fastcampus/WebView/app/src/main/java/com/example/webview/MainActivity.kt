package com.example.webview

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.webview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val container = binding.container

        binding.button1.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.container, WebViewFragment())
                commit()
            }
        }
        binding.button2.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.container, WebViewFragment())
                commit()
            }
        }
    }
}