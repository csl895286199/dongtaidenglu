package com.example.a99999;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.sht.homework.R;

public class Web extends AppCompatActivity {

    private String URI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view);
        WebView webView = (WebView) findViewById(R.id.view_web);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        URI = "https://m.baidu.com/s?tn=80035161_2_dg&wd=" + name ;
        webView.loadUrl(URI);

    }
}
