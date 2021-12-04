package com.example.a99999;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.sht.homework.R;

public class Toolbar_game extends AppCompatActivity {
    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar_game);
        final WebView webView = (WebView) findViewById(R.id.web_game);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.runoob.com/java/method-override.html");
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);

        webView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                    if (webView != null && webView.canGoBack()) {
                        webView.goBack();
                        return true;

                    }
                    if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
                        if (System.currentTimeMillis() - exitTime > 2000) {
                            Toast.makeText(Toolbar_game.this, "再按一次退出", Toast.LENGTH_SHORT).show();
                            exitTime = System.currentTimeMillis();
                        } else {
                            finish();
                            System.exit(0);

                        }

                    }
                    return true;
                }

                return false;
            }
        });
//        webView.setInitialScale(70);
    }
}
