package com.example.newsapi_tieasnura

import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity


class NewsPage : AppCompatActivity() {

    private lateinit var webView: WebView
    lateinit var url: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_page)
        url = intent.getStringExtra("url").toString()
        webView = findViewById(R.id.news_web)

        val settings = webView.settings
        settings.setDomStorageEnabled(true);
        settings.javaScriptEnabled = true


        webView.loadUrl(url)
    }

}