package com.three.enjoytheworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import adapter.FindZhuanTi_LV_Adapteer;
import bean.FindZhuanTiBean;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import uri.FindUri;

public class ZhuanTiActivity extends AppCompatActivity {
  private ImageView imageViewBack;
    private TextView textView;
    private ListView listView;
    private OkHttpClient okHttpClient;
    private Request request;
    private Call call;
    private List<FindZhuanTiBean.ItemListBean> listBeen=new ArrayList<>();
    private FindZhuanTi_LV_Adapteer adapter;
    private boolean flag=false;
    private FindZhuanTiBean totalBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuan_ti);
        initView();
        Intent intent=getIntent();
        String name=intent.getStringExtra("name");
        textView.setText(name);
        adapter=new FindZhuanTi_LV_Adapteer(ZhuanTiActivity.this,listBeen);
        listView.setAdapter(adapter);
        loadData(FindUri.ZhuanTi);
        //listview的加载更多的监听
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                  if(flag==true && scrollState==0)
                  {
                      String nextPageUrl=totalBean.getNextPageUrl();
                      if(nextPageUrl!=null)
                      {
                          loadData(nextPageUrl);
                      }
                  }
            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                 if(firstVisibleItem+visibleItemCount==totalItemCount)
                 {
                     flag=true;
                 }else {
                     flag=false;
                 }
            }
        });
        //设置listview的点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String actionUrl = listBeen.get(i).getData().getActionUrl();
                try {
                    //将ActionURL进行解码
                    String path = URLDecoder.decode(actionUrl, "UTF-8");
                    Log.i("uuu", "=======: " + path);
                    //得到"title"在字符串中第一次出现的位置,其实是title中字符t在整个字符串中的位置
                    int titlePosition = path.indexOf("title");
                    int urlPosition = path.indexOf("url");
                    String title = path.substring(titlePosition + 6, urlPosition - 1);
                    String weburl = path.substring(urlPosition + 4);
                    Log.i("uuu", "==weburl=== " + weburl);
                    Log.i("uuu", "===title===: " + title);
                    Intent intent = new Intent(ZhuanTiActivity.this, FindSecond_TwoActivity.class);
                    intent.putExtra("title", title);
                    intent.putExtra("weburl", weburl);
                    startActivity(intent);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });
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
                String str=response.body().string();
                Log.i("ppp", "onResponse: "+str);
                Gson gson=new Gson();
                totalBean=gson.fromJson(str,FindZhuanTiBean.class);
                final List<FindZhuanTiBean.ItemListBean> newbean=totalBean.getItemList();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        listBeen.addAll(newbean);
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    private void initView() {
        imageViewBack= (ImageView) findViewById(R.id.back);
        textView= (TextView) findViewById(R.id.tv);
        listView= (ListView) findViewById(R.id.listview);
    }
}
