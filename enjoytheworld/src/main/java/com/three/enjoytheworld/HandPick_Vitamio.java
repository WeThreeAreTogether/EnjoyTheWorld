package com.three.enjoytheworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class HandPick_Vitamio extends AppCompatActivity {
    private VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                WindowManager.LayoutParams. FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hand_pick__vitamio);
        initView();
        Intent intent=getIntent();
        //初始化
        Vitamio.isInitialized(this);
        //
        videoView.setVideoPath(intent.getStringExtra("url"));
//        Toast.makeText(getApplicationContext(),""+intent.getStringExtra("url"),Toast.LENGTH_SHORT).show();
        //
        videoView.setMediaController(new MediaController(this));
        //
        videoView.start();
    }

    private void initView() {
        videoView= (VideoView) findViewById(R.id.videoView_show);
    }

}
