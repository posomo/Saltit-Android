package com.posomo.saltit.place_info

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import com.posomo.saltit.place_info.databinding.ActivityCommonWebViewBinding

class CommonWebViewActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var binding: ActivityCommonWebViewBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommonWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        webView = binding.webView.apply {
            webViewClient = WebViewClient()
            webChromeClient = WebChromeClient()
            settings.loadWithOverviewMode = true
            settings.useWideViewPort = true
            settings.setSupportZoom(false)
            settings.builtInZoomControls = false
            settings.javaScriptEnabled = true
            settings.javaScriptCanOpenWindowsAutomatically = true
            settings.setSupportMultipleWindows(true)
            loadUrl(intent.getStringExtra("url") ?: return)
        }
    }
}