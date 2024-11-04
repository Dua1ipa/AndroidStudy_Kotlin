package com.example.webview

import android.graphics.Bitmap
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar

class WebtoonWebViewClient(private val progressBar: ProgressBar) : WebViewClient() {

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        if (request != null && request.url.toString().contains("comic.naver.com"))
            return false
        else return true  //URL을 로드하지 않게 만든다
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)

        progressBar.visibility = View.GONE
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)

        progressBar.visibility = View.VISIBLE
    }

}