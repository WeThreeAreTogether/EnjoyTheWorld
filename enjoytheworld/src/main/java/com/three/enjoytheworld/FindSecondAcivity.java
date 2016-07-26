package com.three.enjoytheworld;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

public class FindSecondAcivity extends AppCompatActivity {
private ImageView imageViewBack,imageViewShare,imageViewFocus;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_second_acivity);
        initView();
    }

    private void initView() {
        imageViewBack= (ImageView) findViewById(R.id.back);
        imageViewShare= (ImageView) findViewById(R.id.share);
        imageViewFocus= (ImageView) findViewById(R.id.focus);
        listView= (ListView) findViewById(R.id.listview);
    }
}
