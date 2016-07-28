package com.three.enjoytheworld;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import adapter.FindHotPopular_VP_Adapter;
import fragment.FindHotPopularFragment;
import fragment.FindQJFragment;

public class FindThreeActivity extends AppCompatActivity {
   private ImageView imageViewBack,imageViewShare,imageViewFocus;
    private TextView textViewTilte;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<String> tabList=new ArrayList<>();
    private List<Fragment> fragmentList=new ArrayList<>();
    private FindHotPopular_VP_Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_three);
        initView();
        Intent intent=getIntent();
        String name=intent.getStringExtra("name");
        int id=intent.getIntExtra("id",-1);
        textViewTilte.setText(name);
        tabLayout.setupWithViewPager(viewPager);
        if(name.equals("最受欢迎"))
        {
            tabList.add("周排行");
            tabList.add("月排行");
            tabList.add("总排行");
            for (int i = 0; i < tabList.size(); i++) {
                FindHotPopularFragment fragment=new FindHotPopularFragment();
                Bundle bundle=new Bundle();
                bundle.putInt("flag",i);
                fragment.setArguments(bundle);
                fragmentList.add(fragment);
            }
            adapter=new FindHotPopular_VP_Adapter(getSupportFragmentManager(),fragmentList,tabList);
            viewPager.setAdapter(adapter);

        }else
        {
                tabList.add("按时间排序");
                tabList.add("按分享排序");
                for (int i = 0; i < tabList.size(); i++) {
                    FindQJFragment fragment=new FindQJFragment();
                    Bundle bundle=new Bundle();
                    bundle.putInt("flag",i);
                    bundle.putInt("id",id);
                    fragment.setArguments(bundle);
                    fragmentList.add(fragment);
                    adapter=new FindHotPopular_VP_Adapter(getSupportFragmentManager(),fragmentList,tabList);
                    viewPager.setAdapter(adapter);
            }

        }

        //设置监听
        setListener();
    }

    private void setListener() {
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initView() {
        imageViewBack= (ImageView) findViewById(R.id.back);
        imageViewFocus= (ImageView) findViewById(R.id.focus);
        imageViewShare= (ImageView) findViewById(R.id.share);
        textViewTilte= (TextView) findViewById(R.id.tv);
        tabLayout= (TabLayout) findViewById(R.id.tablayout);
        viewPager= (ViewPager) findViewById(R.id.viewpager);
    }
}
