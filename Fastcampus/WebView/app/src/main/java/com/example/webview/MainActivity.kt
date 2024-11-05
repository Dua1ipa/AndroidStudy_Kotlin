package com.example.webview

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.webview.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager.adapter = ViewPagerAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            run {
                val textView = TextView(this@MainActivity)
                textView.text = "position $position"
                textView.gravity = android.view.Gravity.CENTER
                tab.customView = textView

//                tab.text = "position $position"
            }
        }.attach()
    }

    override fun onBackPressed() {
        val currentFragment = supportFragmentManager.fragments[binding.viewPager.currentItem]
        if (currentFragment is WebViewFragment) {
            if (currentFragment.canGoBack())  //이전 페이지가 있으면 true, 없으면 false
                currentFragment.goBack()     //이전 페이지로 이동
            else
                super.onBackPressed()       //뒤로가기를 통한 엑티비티 종료
        } else {
            super.onBackPressed()
        }
    }

}