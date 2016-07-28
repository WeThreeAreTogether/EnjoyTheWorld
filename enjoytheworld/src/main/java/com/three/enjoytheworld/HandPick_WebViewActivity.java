package com.three.enjoytheworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

public class HandPick_WebViewActivity extends AppCompatActivity {
    private TextView titleTextView;
    private WebView webView;
    private String title;
    private String web_URL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hand_pick__web_view);
        getData();
        initView();
        setData();
    }

    private void getData() {
        Intent intent=getIntent();
        Bundle bundle= intent.getExtras();
        title=bundle.getString("title");
        web_URL=bundle.getString("web_URL");

    }

    private void setData() {
        titleTextView.setText(title);
        webView.loadUrl(web_URL);
    }

    private void initView() {
        titleTextView= (TextView) findViewById(R.id.handpick_web_text_1);
        webView= (WebView) findViewById(R.id.handpick_web_view);
    }

    public void imgBackOnClick(View view) {
        finish();
    }
}
