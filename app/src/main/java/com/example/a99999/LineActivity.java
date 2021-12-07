package com.example.a99999;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.sht.homework.R;

public class LineActivity extends AppCompatActivity {
    private long exitTime = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.line);
        WebView webView = findViewById(R.id.web_line);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSupportZoom(true);
        webView.loadUrl("https://www.jianshu.com/p/e95b714d60f9");
        webView.getSettings().setBuiltInZoomControls(true);
        webView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                    if (webView != null && webView.canGoBack()) {
                        webView.goBack();
                        return true;
                    }
                }
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
                    if ( System.currentTimeMillis()-exitTime > 2000) {
                        exitTime = System.currentTimeMillis();
                        Toast.makeText(LineActivity.this, "再按一次退出", Toast.LENGTH_SHORT).show();
                    } else {
                        finish();
                        System.exit(0);
                    }
                    return true;
                }
                return false;
            }
        });
        webView.setInitialScale(70);
    }
}
