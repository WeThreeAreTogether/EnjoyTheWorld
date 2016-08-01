package com.three.enjoytheworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

public class FindSecond_TwoActivity extends AppCompatActivity {
private TextView textViewTitle;
    private WebView webView;
    private WebSettings webSettings;
    private ImageView imageViewBack;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_second__two);
         initView();
         Intent intent=getIntent();
         String title=intent.getStringExtra("title");
         String weburl=intent.getStringExtra("weburl");
         textViewTitle.setText(title);
         webSettings=webView.getSettings();
         webSettings.setJavaScriptEnabled(true);
         webView.setWebViewClient(new WebViewClient());
         webView.loadUrl(weburl);

    }

    private void initView() {
        textViewTitle= (TextView) findViewById(R.id.title_show);
        webView= (WebView) findViewById(R.id.webview);
        imageViewBack= (ImageView) findViewById(R.id.back);
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
