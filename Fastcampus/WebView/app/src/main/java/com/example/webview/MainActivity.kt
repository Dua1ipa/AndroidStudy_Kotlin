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

    override fun onBackPressed() {
        val currentFragment = supportFragmentManager.fragments[0]
        if(currentFragment is WebViewFragment){
            if(currentFragment.canGoBack())  //이전 페이지가 있으면 true, 없으면 false
                currentFragment.goBack()     //이전 페이지로 이동
            else
                super.onBackPressed()       //뒤로가기를 통한 엑티비티 종료
        }else{
            super.onBackPressed()
        }
    }
}