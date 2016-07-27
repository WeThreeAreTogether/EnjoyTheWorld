package com.three.enjoytheworld;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import uri.FindUri;

public class FindSecondAcivity extends AppCompatActivity {
private ImageView imageViewBack,imageViewShare,imageViewFocus;
    private ListView listView;
    private OkHttpClient okHttpClient;
    private Request request;
    private Call call;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_second_acivity);
        initView();
        loadData(FindUri.ViewPager_One);
    }

    private void loadData(String path) {
        okHttpClient=new OkHttpClient();
        request=new Request.Builder()
                            .url(path)
                            .build();
        call=okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }

    private void initView() {
        imageViewBack= (ImageView) findViewById(R.id.back);
        imageViewShare= (ImageView) findViewById(R.id.share);
        imageViewFocus= (ImageView) findViewById(R.id.focus);
        listView= (ListView) findViewById(R.id.listview);
    }
}
