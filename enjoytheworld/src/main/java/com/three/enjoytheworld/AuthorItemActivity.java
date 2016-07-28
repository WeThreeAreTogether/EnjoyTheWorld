package com.three.enjoytheworld;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import adapter.AuthorItemViewPagerAdapter;
import fragment.AuthorItemFragment;

public class AuthorItemActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> mFragments;
    private int itemId;
    private TextView tv_title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author_item);

        getSupportActionBar().hide();
        itemId = getIntent().getIntExtra("itemId",0);

        initView();



    }


    private void initView() {
        tabLayout = (TabLayout) findViewById(R.id.tabLayout_author);
        viewPager = (ViewPager) findViewById(R.id.viewpager_author);
        tv_title = (TextView) findViewById(R.id.tv_author_title);
        tv_title.setText(getIntent().getStringExtra("title"));

        mFragments = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            AuthorItemFragment fragment = new AuthorItemFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("index",i);
            bundle.putInt("itemId",itemId);
            fragment.setArguments(bundle);
            mFragments.add(fragment);
        }

        viewPager.setAdapter(new AuthorItemViewPagerAdapter(getSupportFragmentManager(),mFragments));

        tabLayout.setupWithViewPager(viewPager);

    }

    public void click(View view) {
        switch (view.getId()){
            case R.id.iv_author_back:
                finish();
                break;
            case R.id.tv_author_name:
                break;
            case R.id.iv_author_share:
                break;
        }
    }
}
