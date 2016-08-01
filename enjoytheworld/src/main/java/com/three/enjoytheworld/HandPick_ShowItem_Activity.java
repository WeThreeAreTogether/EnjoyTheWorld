package com.three.enjoytheworld;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kogitune.activity_transition.ActivityTransition;
import com.kogitune.activity_transition.ExitActivityTransition;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import io.vov.vitamio.widget.VideoView;
import utils.HandPick_All_Static_Obj;
import utils.TransFormation;

public class HandPick_ShowItem_Activity extends Activity implements GestureDetector.OnGestureListener{
    //视频上的图片展示
    private ImageView videoImage;
    //返回图片
    private ImageView backImage;
    //展示activity的图片
    private ImageView showMoreImage;
    //收藏图片
    private ImageView collectImage;
    //分享图片
    private ImageView shareImage;
    //留言图片
    private ImageView messageImage;
    //缓存图片
    private ImageView londedImage;
    //更多视频的圆图标
    private ImageView moreRightImage;
    //更多右箭头图片
    private ImageView tMoreRightArrowImage;


    //标题文本
    private TextView titleText;
    //事件文本
    private TextView timeText;
    //描述文本
    private TextView describeText;

    //收藏文本
    private TextView collectText;
    //分享文本
    private TextView shareText;
    //留言文本
    private TextView messageText;
    //缓存文本
    private TextView londedText;
    //第二个title
    private TextView secondTitle;
    //视频数
    private TextView videoCount;
    //第二个描述
    private TextView secondDescribe;

    //设置bitmap,给image展示的设置bitmap
    private Bitmap bitmap;

    private ExitActivityTransition exitTransition;
    //包含imageview的layout
    private RelativeLayout relativeLayout;
    //展示图片之下的布局
    private RelativeLayout relativeLayoutMessage;

    //传递一个Bundle用来接收数据
    private Bundle bundle = null;
    //背景图片的String链接
    private String backgroundUrl;
    //背景图片
    private ImageView background_imageview;

    //含有第二个更多的布局
    private RelativeLayout handpick_head_relative_layout_3;

    private VideoView videoView;

    //添加手势
    private GestureDetector gestureDetector;


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    relativeLayoutMessage.setVisibility(View.VISIBLE);
                    backImage.setVisibility(View.VISIBLE);
                    backImageAlphAnimation(backImage, 1);
                    relativeTransAnimation(relativeLayoutMessage, 1);
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setTheme(R.style.mytranslucent);
//        getSupportActionBar().hide();
        ////////////////动态的将这个activity设置成透明的主题
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hand_pick__show_item_activity);

        initView();
        getData();
        setData();

//        relativeLayout= (RelativeLayout) findViewById(R.id.cover_img);
        //展示图片之下的布局隐藏掉
        relativeLayoutMessage.setVisibility(View.GONE);
        //返回的按钮隐藏掉
        backImage.setVisibility(View.GONE);






        //设置启动方式
//        ActivityTransition.with(getIntent()).to(relativeLayout).start(savedInstanceState);
        new Thread(new Runnable() {
            @Override
            public void run() {
                handler.sendEmptyMessageDelayed(0, 300);
            }
        }).start();


//        handler.sendEmptyMessageAtTime(0,3000);
        //设置退出方式
        exitTransition = ActivityTransition.with(getIntent()).to(relativeLayout).duration(300).start(savedInstanceState);
        /*//先隐藏vitamio
        videoView.setVisibility(View.GONE);*/

    }

    private void getData() {
        Intent intent = getIntent();
        bundle = intent.getExtras();
    }

    private void setData() {
        videoImage.setImageBitmap(HandPick_All_Static_Obj.bitmap);
        HandPick_All_Static_Obj.bitmap = null;
        imgObjAnimation(videoImage);

        titleText.setText(bundle.getString("title"));
        timeText.setText(bundle.getString("time"));

        describeText.setText(bundle.getString("describe"));

        /*bundle.putString("collectionCount",""+list.get(position).getData().getConsumption().getCollectionCount());
                        bundle.putString("shareCount",""+list.get(position).getData().getConsumption().getShareCount());
                        bundle.putString("replyCount",""+list.get(position).getData().getConsumption().getReplyCount());
           */
        collectText.setText(bundle.getString("collectionCount"));
        shareText.setText(bundle.getString("shareCount"));
        messageText.setText(bundle.getString("replyCount"));

        getPicassoImageData(bundle.getString("background_img_url"), background_imageview, 2);


        /*
        bundle.putString("image_show_second",list.get(position).getData().getAuthor().getIcon());
        bundle.putString("second_title",list.get(position).getData().getAuthor().getName());
        bundle.putString("second_describe",list.get(position).getData().getAuthor().getDescription());
        bundle.putString("video_num",""+list.get(position).getData().getAuthor().getVideoNum());
//
 //更多视频的圆图标
    private ImageView moreRightImage;
    //更多右箭头图片
    private ImageView tMoreRightArrowImage;
        * */
        if (HandPick_All_Static_Obj.isNotEmpty){
            handpick_head_relative_layout_3.setVisibility(View.VISIBLE);
            getPicassoImageData(bundle.getString("image_show_second"), moreRightImage, 1);
            secondTitle.setText(bundle.getString("second_title"));
            videoCount.setText(bundle.getString("video_num"));
            secondDescribe.setText(bundle.getString("second_describe"));
            HandPick_All_Static_Obj.isNotEmpty=false;
        }else{
            handpick_head_relative_layout_3.setVisibility(View.GONE);
        }



    }

    private void initView() {
        //视频上的图片展示
        videoImage = (ImageView) findViewById(R.id.handpick_showitem_img_1);
        //返回图片
        backImage = (ImageView) findViewById(R.id.handpick_showitem_img_3);
        //展示activity更多的图片
        showMoreImage = (ImageView) findViewById(R.id.handpick_showitem_img_2);
        //收藏图片
        collectImage = (ImageView) findViewById(R.id.handpick_showitem_img_4);
        //分享图片
        shareImage = (ImageView) findViewById(R.id.handpick_showitem_img_5);
        //留言图片
        messageImage = (ImageView) findViewById(R.id.handpick_showitem_img_6);
        //缓存图片
        londedImage = (ImageView) findViewById(R.id.handpick_showitem_img_7);

        //标题文本
        titleText = (TextView) findViewById(R.id.handpick_showitem_text_1);
        //事件文本
        timeText = (TextView) findViewById(R.id.handpick_showitem_text_2);
        //描述文本
        describeText = (TextView) findViewById(R.id.handpick_showitem_text_describe);

        //收藏文本
        collectText = (TextView) findViewById(R.id.handpick_showitem_text_3);
        //分享文本
        shareText = (TextView) findViewById(R.id.handpick_showitem_text_4);
        //留言文本
        messageText = (TextView) findViewById(R.id.handpick_showitem_text_5);
        //缓存文本
        londedText = (TextView) findViewById(R.id.handpick_showitem_text_6);

        //第二个title
        secondTitle = (TextView) findViewById(R.id.title_2);
        //视频数
        videoCount = (TextView) findViewById(R.id.video_count);
        //第二个描述
        secondDescribe = (TextView) findViewById(R.id.describe_2);

        //包含展示图片的layout
        relativeLayout = (RelativeLayout) findViewById(R.id.cover_img);
        //展示图片之下的layout
        relativeLayoutMessage = (RelativeLayout) findViewById(R.id.handpick_showitem_layotu_should_hind_first_1);
        //背景图片
        background_imageview = (ImageView) findViewById(R.id.img_message_background);
        ////更多视频的圆图标
        moreRightImage = (ImageView) findViewById(R.id.image_show_second_activity);
        //更多右箭头图片
        tMoreRightArrowImage = (ImageView) findViewById(R.id.right_action_more_arrowaction_more_arrow);
        handpick_head_relative_layout_3= (RelativeLayout) findViewById(R.id.handpick_head_relative_layout_3);
        //vitamio
//        videoView= (VideoView) findViewById(R.id.video_view_1_show);
        //初始化手势
        gestureDetector=new GestureDetector(HandPick_ShowItem_Activity.this,this);
    }

    //重写的返回按钮
    //或者重写onKeyDown方法
    /*public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            System.out.println("按下了back键   onKeyDown()");
            return false;
        }else {
            return super.onKeyDown(keyCode, event);
        }

    }*/
    //重写的返回按钮
    @Override
    public void onBackPressed() {
        relativeTransAnimation(relativeLayoutMessage, 2);
        backImageAlphAnimation(backImage, 2);
    }


    //图片放大属性动画
    private void imgObjAnimation(final ImageView imageView) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(10000);
        //动画持续的动作  Animation.INFINITE永远执行
        scaleAnimation.setRepeatCount(Animation.INFINITE);
        //动画的重复模式 折返
        scaleAnimation.setRepeatMode(Animation.REVERSE);
        imageView.startAnimation(scaleAnimation);

    }

    private void relativeTransAnimation(final RelativeLayout relativeLayout, int in_Off) {

        AlphaAnimation animation = null;
        switch (in_Off) {
            case 1:
                animation = new AlphaAnimation(0, 1);
                animation.setDuration(1000);
                animation.setFillAfter(true);
                break;
            case 2:
                animation = new AlphaAnimation(1, 0);
                animation.setDuration(1000);
                animation.setFillAfter(true);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        relativeLayoutMessage.setVisibility(View.GONE);
                        backImage.setVisibility(View.GONE);
                        exitTransition.exit(HandPick_ShowItem_Activity.this);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                break;
        }
        relativeLayout.startAnimation(animation);
    }


    public void showItemImgOnClick(View view) {
        switch (view.getId()) {
            //视频展示的图
            case R.id.handpick_showitem_img_1:
                Intent intent=new Intent(HandPick_ShowItem_Activity.this,HandPick_Vitamio.class);
                intent.putExtra("url",bundle.getString("video_url"));
                startActivity(intent);
                break;
            //返回
            case R.id.handpick_showitem_img_3:
                relativeTransAnimation(relativeLayoutMessage, 2);
                backImageAlphAnimation(backImage, 2);
                break;
            //更多
            case R.id.handpick_showitem_img_2:
                startActivity(new Intent(HandPick_ShowItem_Activity.this,HandPick_ShowMore_Item_1_Activity.class));
                break;
            //收藏
            case R.id.handpick_showitem_img_4:
                break;
            //分享
            case R.id.handpick_showitem_img_5:
                break;
            //留言
            case R.id.handpick_showitem_img_6:
                break;
            //缓存
            case R.id.handpick_showitem_img_7:
                break;
        }
    }

    //用户展示的图片
    private void getPicassoImageData(String imgPath, ImageView imageView, int i) {
        //1 加载用户头像,2 加载用户展示图片
        switch (i) {
            case 1:
                Picasso.with(this)
                        .load(imgPath)
                        .config(Bitmap.Config.RGB_565)
                        .resize(120, 120)
                        .transform(new Transformation() {
                            @Override
                            public Bitmap transform(Bitmap source) {
                                Bitmap bitmap = TransFormation.toRoundBitmap(source);
                                if (bitmap != source) {
                                    source.recycle();
                                }
                                return bitmap;
                            }

                            @Override
                            public String key() {
                                return "TransForm";
                            }
                        })
                        .into(imageView);
                break;
            case 2:
                //终点,看笔记
//                imageView.setAdjustViewBounds(true);
//                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                Picasso.with(this)
                        .load(imgPath)
                        .placeholder(R.mipmap.img_back_download_error)
                        .config(Bitmap.Config.RGB_565)
                        .into(imageView);
/*                imageView.buildDrawingCache();
                bitmap=imageView.getDrawingCache();*/
                //写一个全局的参数,进行bitm的传送
                break;
        }
    }

    //返回按钮出现,小时的动画
    private void backImageAlphAnimation(ImageView imageView, int in_Off) {

        AlphaAnimation animation = null;
        switch (in_Off) {
            case 1:
                animation = new AlphaAnimation(0, 1);
                break;
            case 2:
                animation = new AlphaAnimation(1, 0);
        }
        animation.setDuration(1000);
        animation.setFillAfter(true);
        imageView.startAnimation(animation);
    }

    //重写onTouchEvent
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    //-------------------------------------手势操作----------------------------------------
    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        if (motionEvent.getY()-motionEvent1.getY()>0){
            startActivity(new Intent(HandPick_ShowItem_Activity.this,HandPick_ShowMore_Item_1_Activity.class));
        }
        return false;
    }
}
