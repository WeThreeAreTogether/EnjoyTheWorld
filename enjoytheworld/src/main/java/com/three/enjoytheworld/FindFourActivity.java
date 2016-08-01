package com.three.enjoytheworld;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import JazzyViewPager.JazzyViewPager;
import adapter.Find_JazzpVP_Adapter;
import bean.FindPopular_LVBean;
import fragment.Find_JazzpVP_Fragment;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import uri.FindUri;

public class FindFourActivity extends FragmentActivity{
   private TextView textViewTitle,textViewTotalCount,textViewCurrentCount;
    private ImageView imageViewFavorite,imageViewBack,imageViewBackground;
    private OkHttpClient okHttpClient;
    private Request request;
    private Call call;
    private FindPopular_LVBean lvBean;
    private List<FindPopular_LVBean.ItemListBean> itemList=new ArrayList<>();
    private JazzyViewPager mJazzy;
    private List<Fragment> listFragment=new ArrayList<>();
    private Find_JazzpVP_Adapter adapter;
    private List<String> listImage=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_four);
        initView();
        Intent intent=getIntent();
        String name=intent.getStringExtra("name");
        textViewTitle.setText(name);
        //加载数据
        loadData(FindUri.ViewPager_Two);
        setupJazziness(JazzyViewPager.TransitionEffect.CubeOut);
        //设置ViewPager的页卡切换监听事件
        listenPagerChange();
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void listenPagerChange() {
        mJazzy.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                 textViewCurrentCount.setText((position+1)+"");
                Picasso.with(FindFourActivity.this).load(listImage.get(position))
                        .placeholder(R.mipmap.ic_eye_black_error)
                        .error(R.mipmap.ic_eye_black_error)
                        .config(Bitmap.Config.RGB_565)
                        .into(imageViewBackground);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setupJazziness(JazzyViewPager.TransitionEffect effect) {
        mJazzy = (JazzyViewPager) findViewById(R.id.jazzyviewpager);
        mJazzy.setTransitionEffect(effect);
        adapter=new Find_JazzpVP_Adapter(getSupportFragmentManager(),listFragment,mJazzy);
        mJazzy.setAdapter(adapter);
        mJazzy.setPageMargin(30);
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
                Log.i("iii", "=====JazzyViewPager=======: "+str);
                Gson gson=new Gson();
                lvBean=gson.fromJson(str,FindPopular_LVBean.class);
                itemList.addAll(lvBean.getItemList());
               // textViewTotalCount.setText(itemList.size()+"");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textViewTotalCount.setText(itemList.size()+"");
                        for (int i = 0; i < itemList.size(); i++) {
                            Find_JazzpVP_Fragment fragment=new Find_JazzpVP_Fragment();
                            Bundle bundle=new Bundle();
                            bundle.putSerializable("data",itemList.get(i).getData());
                            fragment.setArguments(bundle);
                            listFragment.add(fragment);
                            listImage.add(itemList.get(i).getData().getCover().getBlurred());
                        }
                        adapter.notifyDataSetChanged();
                        //默认展示第一张
                        Picasso.with(FindFourActivity.this).load(listImage.get(0))
                                .placeholder(R.mipmap.ic_eye_black_error)
                                .error(R.mipmap.ic_eye_black_error)
                                .config(Bitmap.Config.RGB_565)
                                .into(imageViewBackground);
                        textViewCurrentCount.setText(1+"");
                    }
                });

            }
        });
    }

    private void initView() {
        textViewTitle= (TextView) findViewById(R.id.title);
        textViewCurrentCount= (TextView) findViewById(R.id.pager);
        textViewTotalCount= (TextView) findViewById(R.id.pagerTotal);
        imageViewBack= (ImageView) findViewById(R.id.back);
        imageViewBackground= (ImageView) findViewById(R.id.background);
        imageViewFavorite= (ImageView) findViewById(R.id.favorite);
    }
}
