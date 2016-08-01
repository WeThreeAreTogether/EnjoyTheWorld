package com.three.enjoytheworld;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kogitune.activity_transition.ActivityTransition;
import com.kogitune.activity_transition.ExitActivityTransition;
import com.squareup.picasso.Picasso;

import bean.FindPopular_LVBean;
import bean.Find_QJ_Bean;
import utils.HandPick_All_Static_Obj;

public class FindFiveActivity extends Activity {
    private ImageView imageViewBackgound,imageViewTupian,imageViewStart;
    private TextView textViewTitle,textViewCategory,textViewTime,textViewDetail;
    private TextView textViewFavorite,textViewShare,textViewTalk,textViewSave;
    private ExitActivityTransition transition;
    Intent intent;
    FindPopular_LVBean.ItemListBean.DataBean dataBean;
    private Find_QJ_Bean.ItemListBean.DataBean dataBean1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_five);
        initView();
        intent=getIntent();
        Bundle bundle=intent.getExtras();
        int flag=intent.getIntExtra("flag",-1);
        if(flag==0)
        {
            dataBean= (FindPopular_LVBean.ItemListBean.DataBean) bundle.getSerializable("data");
            loadData();
            transition=  ActivityTransition.with(intent).to(imageViewTupian).start(savedInstanceState);
            imageViewTupian.setImageBitmap(HandPick_All_Static_Obj.bitmap);
            HandPick_All_Static_Obj.bitmap=null;
        }
       else if(flag==1){
            dataBean1= (Find_QJ_Bean.ItemListBean.DataBean) bundle.getSerializable("data");
            loadData2();
            transition=  ActivityTransition.with(intent).to(imageViewTupian).start(savedInstanceState);
//            imageViewTupian.setImageBitmap(HandPick_All_Static_Obj.bitmap1);
//            HandPick_All_Static_Obj.bitmap1=null;
           Picasso.with(FindFiveActivity.this).load(dataBean1.getCover().getDetail()).into(imageViewTupian);

        }


    }

    private void loadData2() {
        textViewTitle.setText(dataBean1.getTitle()+"");
        textViewCategory.setText(dataBean1.getCategory()+"");
        int totalTime=dataBean1.getDuration();
        if(totalTime>0)
        {
            int minute=totalTime/60;
            int seconds=totalTime%60;
            textViewTime.setText(minute+"'"+seconds+"\"");
        }else {
            textViewTime.setText("");
        }
        textViewDetail.setText(dataBean1.getDescription());
        textViewFavorite.setText(dataBean1.getConsumption().getCollectionCount()+"");
        textViewShare.setText(dataBean1.getConsumption().getShareCount()+"");
        textViewTalk.setText(dataBean1.getConsumption().getReplyCount()+"");
        Picasso.with(FindFiveActivity.this).load(dataBean1.getCover().getBlurred())
                .placeholder(R.mipmap.ic_eye_black_error)
                .error(R.mipmap.ic_eye_black_error)
                .into(imageViewBackgound);
        imageViewStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FindFiveActivity.this,HandPick_Vitamio.class);
                intent.putExtra("url",dataBean1.getPlayUrl());
                startActivity(intent);
            }
        });
    }

    private void loadData() {
        textViewTitle.setText(dataBean.getTitle()+"");
        textViewCategory.setText(dataBean.getCategory()+"");
        int totalTime=dataBean.getDuration();
        if(totalTime>0)
        {
            int minute=totalTime/60;
            int seconds=totalTime%60;
            textViewTime.setText(minute+"'"+seconds+"\"");
        }else {
            textViewTime.setText("");
        }
        textViewDetail.setText(dataBean.getDescription());
        textViewFavorite.setText(dataBean.getConsumption().getCollectionCount()+"");
        textViewShare.setText(dataBean.getConsumption().getShareCount()+"");
        textViewTalk.setText(dataBean.getConsumption().getReplyCount()+"");
        Picasso.with(FindFiveActivity.this).load(dataBean.getCover().getBlurred())
                                   .placeholder(R.mipmap.ic_eye_black_error)
                                   .error(R.mipmap.ic_eye_black_error)
                                  .into(imageViewBackgound);
        imageViewStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FindFiveActivity.this,HandPick_Vitamio.class);
                intent.putExtra("url",dataBean.getPlayUrl());
                startActivity(intent);
            }
        });

    }

    private void initView() {
        imageViewBackgound= (ImageView) findViewById(R.id.background_total);
        imageViewTupian= (ImageView) findViewById(R.id.video_imageview);
        imageViewStart= (ImageView) findViewById(R.id.video_start);
        textViewTitle= (TextView) findViewById(R.id.title_show);
        textViewCategory= (TextView) findViewById(R.id.category);
        textViewTime= (TextView) findViewById(R.id.time_show);
        textViewDetail= (TextView) findViewById(R.id.detail);
        textViewFavorite= (TextView) findViewById(R.id.favorite_text);
        textViewShare= (TextView) findViewById(R.id.share_text);
        textViewTalk= (TextView) findViewById(R.id.talk_show);
        textViewSave= (TextView) findViewById(R.id.storage_text);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        transition.exit(this);
    }
}
