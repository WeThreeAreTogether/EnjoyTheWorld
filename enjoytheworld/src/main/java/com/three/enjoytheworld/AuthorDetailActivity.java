package com.three.enjoytheworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.kogitune.activity_transition.ActivityTransition;
import com.kogitune.activity_transition.ExitActivityTransition;
import com.squareup.picasso.Picasso;

import bean.AuthorBean;
import bean.AuthorItemBean;

public class AuthorDetailActivity extends AppCompatActivity{

    private static final String TAG = AuthorDetailActivity.class.getSimpleName();
    private ImageView iv_detail;
    private ExitActivityTransition mStart;
    /**
     * 跳转页面Intent传过来的值，当标记为1时，共享视图
     */
    private int flag;
    private ImageView iv_back;
    private ImageView iv_blurred;

    private TextView tv_title;
    private TextView tv_category;
    private TextView tv_duration;

//    private ImageView iv_item_icon;
//    private TextView tv_item_title;
//    private TextView tv_item_subTitle;
//    private TextView tv_item_description;

    private TextView tv_description;
    private TextView tv_collect;
    private TextView tv_share;
    private TextView tv_reply;


    private AuthorItemBean.ItemListBean.DataBean data1;
    private String mTitle;
    private String mCategory;
    private int mDuration;
    private String mDescription;
    private int mCollectionCount;
    private int mReplyCount;
    private int mShareCount;
    private String mDetail;
    private String mBlurred;
    private AuthorBean.ItemListBean.DataBean.ItemListBean2.DataBean2 data2;
    private String mPlayUrl;

    private boolean isNotFinish = true;

//    private Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            setAnimation();
//        }
//    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author_detail);

        getSupportActionBar().hide();


        initActivity(savedInstanceState);


        initView();

//        while (isNotFinish){
//            handler.sendEmptyMessageDelayed(0,2000);
//        }




    }

    /**
     * 为图片设置动画效果
     */
    private void setAnimation() {

        ScaleAnimation magnifyAnimation = new ScaleAnimation(0, 0, 0, 0,
                ScaleAnimation.RELATIVE_TO_SELF, 1.2f,
                ScaleAnimation.RELATIVE_TO_SELF, 1.2f);
        magnifyAnimation.setDuration(1000);

        ScaleAnimation shrinkAnimation = new ScaleAnimation(0, 0, 0, 0,
                ScaleAnimation.RELATIVE_TO_SELF, 1.2f,
                ScaleAnimation.RELATIVE_TO_SELF, 1.2f);
        shrinkAnimation.setDuration(1000);
        shrinkAnimation.setStartOffset(1000);

    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        isNotFinish = false;
//    }

    private void initActivity(Bundle savedInstanceState) {
        iv_detail = (ImageView) findViewById(R.id.iv_author_detail);
        Intent intent = getIntent();

        //当标记为1时，共享视图
        int flag = intent.getIntExtra("flag",0);
        Log.i(TAG, "onCreate: flag:"+flag);
        if (flag == 1){
            mStart = ActivityTransition.with(intent).to(iv_detail).start(savedInstanceState);
            data1 = (AuthorItemBean.ItemListBean.DataBean) intent.getExtras().getSerializable("data1");
            mTitle = data1.getTitle();
            mCategory = data1.getCategory();
            mDuration = data1.getDuration();
            mDescription = data1.getDescription();
            AuthorItemBean.ItemListBean.DataBean.ConsumptionBean consumption = data1.getConsumption();
            if (consumption != null) {
                mCollectionCount = consumption.getCollectionCount();
                mReplyCount = consumption.getReplyCount();
                mShareCount = consumption.getShareCount();
            }
            AuthorItemBean.ItemListBean.DataBean.CoverBean cover = data1.getCover();
            if (cover != null) {
                mDetail = cover.getDetail();
                mBlurred = cover.getBlurred();
            }
            mPlayUrl = data1.getPlayUrl();

        }else if (flag == 2){
            data2 = ((AuthorBean.ItemListBean.DataBean.ItemListBean2.DataBean2) intent.getExtras().getSerializable("data2"));


            mTitle = data2.getTitle();
            mCategory = data2.getCategory();
            mDuration = data2.getDuration();
            mDescription = data2.getDescription();
            AuthorBean.ItemListBean.DataBean.ItemListBean2.DataBean2.ConsumptionBean consumption = data2.getConsumption();
            if (consumption != null) {
                mCollectionCount = consumption.getCollectionCount();
                mReplyCount = consumption.getReplyCount();
                mShareCount = consumption.getShareCount();
            }
            AuthorBean.ItemListBean.DataBean.ItemListBean2.DataBean2.CoverBean cover = data2.getCover();
            if (cover != null) {
                mDetail = cover.getDetail();
                mBlurred = cover.getBlurred();
            }
            mPlayUrl = data2.getPlayUrl();
        }
    }



    private void initView() {

        if (flag == 2){
            iv_back = (ImageView) findViewById(R.id.iv_author_back);
            iv_back.setBackgroundResource(R.mipmap.ic_action_detail_close);
        }

        if (!TextUtils.isEmpty(mDetail)){
            Picasso.with(this).load(mDetail).into(iv_detail);
        }

        iv_blurred = (ImageView) findViewById(R.id.iv_author_blurred);
        if (!TextUtils.isEmpty(mBlurred)){
            Picasso.with(this).load(mBlurred).into(iv_blurred);
        }



        tv_title = (TextView) findViewById(R.id.tv_author_title);
        if (!TextUtils.isEmpty(mTitle)){
            tv_title.setText(mTitle);
        }

        tv_category= (TextView) findViewById(R.id.tv_author_category);
        if (!TextUtils.isEmpty(mCategory)){
            tv_category.setText("#"+mCategory);
        }

        tv_duration = (TextView) findViewById(R.id.tv_author_duration);
        if (mDuration != 0){
            int minute = mDuration / 60;
            int second = mDuration % 60;
            tv_duration.setText(String.valueOf(minute)+"'"+String.valueOf(second)+"\"");
        }

//        tv_item_title = (TextView) findViewById(R.id.tv_author_item_title);
//        tv_item_title.setTextColor(Color.WHITE);
//        if (!TextUtils.isEmpty(mTitle)){
//            tv_title.setText(mTitle);
//        }
//
//        tv_item_subTitle = (TextView) findViewById(R.id.tv_author_subtitle);
//        tv_item_subTitle.setTextColor(Color.WHITE);
//        if (!TextUtils.isEmpty(mTitle)){
//            tv_title.setText(mTitle);
//        }
//
//        tv_item_description = (TextView) findViewById(R.id.tv_author_item_description);
//        tv_item_description.setTextColor(Color.WHITE);
//        if (!TextUtils.isEmpty(mTitle)){
//            tv_title.setText(mTitle);
//        }
//
//        iv_item_icon = (ImageView) findViewById(R.id.iv_author_icon);
//        if (!TextUtils.isEmpty(mTitle)){
//            tv_title.setText(mTitle);
//        }

        tv_description = (TextView) findViewById(R.id.tv_author_description);
        if (!TextUtils.isEmpty(mDescription)){
            tv_description.setText(mDescription);
        }

        tv_collect= (TextView) findViewById(R.id.tv_author_collect);
        tv_collect.setText(String.valueOf(mCollectionCount));

        tv_share = (TextView) findViewById(R.id.tv_author_share);
        tv_share.setText(String.valueOf(mShareCount));

        tv_reply = (TextView) findViewById(R.id.tv_author_reply);
        tv_reply.setText(String.valueOf(mReplyCount));


    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if (flag == 1){
            mStart.exit(this);
        }else if (flag == 2){
            super.onBackPressed();
        }

    }

    public void click(View view) {

        switch (view.getId()){
            //返回
            case R.id.iv_author_back:
                if (flag == 1){
                    mStart.exit(this);
                }else if (flag == 2){
                    finish();
                }

                break;
//            //更多
//            case R.id.iv_author_more:
//                break;
            //大图点击播放视频
            case R.id.iv_author_detail:
                Intent intent = new Intent(this, AuthorPlayActivity.class);
                intent.putExtra("path",mPlayUrl);
                startActivity(intent);
                break;
            //收藏
            case R.id.iv_author_collect:
                break;
            //分享
            case R.id.iv_author_share:
                break;
            //回复
            case R.id.iv_author_reply:
                break;
            //缓存
            case R.id.iv_author_cache:
                break;



        }
    }


}
