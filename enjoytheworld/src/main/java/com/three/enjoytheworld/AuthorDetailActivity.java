package com.three.enjoytheworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AuthorDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author_detail);

        getSupportActionBar().hide();
    }
}
