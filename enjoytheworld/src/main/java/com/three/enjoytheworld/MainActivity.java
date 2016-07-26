package com.three.enjoytheworld;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import adapter.MainViewPagerFragmentAdapter;
import fragment.AuthorFragment;
import fragment.FindFragment;
import fragment.HandpickFragment;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {

    private RadioGroup mRadioGroup;
    private RadioButton[] mRadioButtons;
    private RadioButton rb_handpick;
    private RadioButton rb_find;
    private RadioButton rb_author;

    private List<Fragment> mFragments;
    private FindFragment mFindFragment;
    private HandpickFragment mHandpickFragment;
    private AuthorFragment mAuthorFragment;



    private ViewPager viewPager;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();


        initView();
    }

    private void initView() {

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);


        viewPager = (ViewPager) findViewById(R.id.viewPager_main);

        mRadioGroup = (RadioGroup) findViewById(R.id.radio_group);
        mRadioButtons = new RadioButton[3];
        rb_handpick = (RadioButton) findViewById(R.id.rb0);
        mRadioButtons[0] = rb_handpick;
        rb_find = (RadioButton) findViewById(R.id.rb1);
        mRadioButtons[1] = rb_find;
        rb_author = (RadioButton) findViewById(R.id.rb2);
        mRadioButtons[2] = rb_author;

        mFragments = new ArrayList<>();
        mHandpickFragment = new HandpickFragment();
        mFragments.add(mHandpickFragment);
        mFindFragment = new FindFragment();
        mFragments.add(mFindFragment);
        mAuthorFragment = new AuthorFragment();
        mFragments.add(mAuthorFragment);


        viewPager.setAdapter(new MainViewPagerFragmentAdapter(getSupportFragmentManager(),mFragments));

        viewPager.setOnPageChangeListener(this);

        mRadioGroup.setOnCheckedChangeListener(this);
    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i){
            case R.id.rb0:
                viewPager.setCurrentItem(0);
                break;
            case R.id.rb1:
                viewPager.setCurrentItem(1);
                break;
            case R.id.rb2:
                viewPager.setCurrentItem(2);
                break;

        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        mRadioButtons[position].setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void click(View view) {
        drawerLayout.openDrawer(Gravity.LEFT);
    }
}
