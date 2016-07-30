package com.three.enjoytheworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class AuthorPlayActivity extends AppCompatActivity {

    private VideoView vv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author_play);

        getSupportActionBar().hide();

        vv = (VideoView) findViewById(R.id.vv_author);
        Vitamio.isInitialized(this);
        vv.setVideoPath(getIntent().getStringExtra("path"));
        vv.setMediaController(new MediaController(this));
        vv.start();
    }
}
