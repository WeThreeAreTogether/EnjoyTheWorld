package com.three.enjoytheworld;

import android.content.Intent;
import android.media.Image;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import adapter.FindVP_LV_Adapter;
import bean.FindLVBean;
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
    private FindLVBean lvBean;
    private List<FindLVBean> lvBeanList=new ArrayList<>();
    FindVP_LV_Adapter adapter;
    private TextView textViewTitle;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.obj.equals(FindUri.ViewPager_One+7922))
            {
                adapter.notifyDataSetChanged();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_second_acivity);
        initView();
        Intent intent=getIntent();
        String title=intent.getStringExtra("title");
        textViewTitle.setText(title);
        adapter=new FindVP_LV_Adapter(FindSecondAcivity.this,lvBeanList);
        listView.setAdapter(adapter);
        for (int i = 7910; i < 7923; i=i+2) {
            if(i!=7918)
            {
                loadData(FindUri.ViewPager_One+i);
            }
        }
       //设置图片的点击监听事件
         setListener();
    }

    private void setListener() {
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //设置动画的监听
        imageViewFocus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //设置分享的监听
        imageViewShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void loadData(final String path) {
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
               String str=response.body().string();
                Gson gson=new Gson();
                lvBean=gson.fromJson(str,FindLVBean.class);
                lvBeanList.add(lvBean);
                Message message=Message.obtain();
                message.obj=path;
                handler.sendMessage(message);
            }
        });
    }

    private void initView() {
        imageViewBack= (ImageView) findViewById(R.id.back);
        imageViewShare= (ImageView) findViewById(R.id.share);
        imageViewFocus= (ImageView) findViewById(R.id.focus);
        listView= (ListView) findViewById(R.id.listview);
        textViewTitle= (TextView) findViewById(R.id.tv);
    }
}
