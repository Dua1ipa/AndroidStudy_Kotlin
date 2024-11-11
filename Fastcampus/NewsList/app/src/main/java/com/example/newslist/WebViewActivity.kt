package com.example.newslist

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.newslist.databinding.ActivityWebviewBinding

class WebViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val url = intent.getStringExtra("URL")
        binding.webView.webViewClient = WebViewClient()
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.settings.domStorageEnabled = true

        if (url.isNullOrEmpty()) {
            finish()
            Toast.makeText(this, "잘못된 URL 입니다.", Toast.LENGTH_SHORT).show()
        } else {
            binding.webView.loadUrl(url)
        }
    }
}