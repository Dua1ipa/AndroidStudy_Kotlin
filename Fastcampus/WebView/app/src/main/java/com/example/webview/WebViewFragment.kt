package com.example.webview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.webview.databinding.FragmentWebviewBinding

class WebViewFragment : Fragment() {
    private lateinit var binding: FragmentWebviewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWebviewBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.webView.webViewClient = WebtoonWebViewClient(binding.progressBar)
        binding.webView.settings.domStorageEnabled = true
        binding.webView.settings.javaScriptEnabled = true

        binding.webView.loadUrl("https://comic.naver.com/")
    }

    fun canGoBack(): Boolean {
        return binding.webView.canGoBack()  //(내장함수) 이전 페이지 기록이 있는지 확인
    }

    fun goBack(){
        binding.webView.goBack()  //(내장함수) 한 단계 뒤로 이동
    }

}