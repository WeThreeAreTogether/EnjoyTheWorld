package adapter;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.three.enjoytheworld.R;

import java.util.ArrayList;
import java.util.List;

import bean.HandPick_Bean;
import recyclerview.recyclerview.XRecylcerView;

/**
 * Created by Sadewangzi on 2016/7/26.
 */
public class HandPick_Adapter extends RecyclerView.Adapter<HandPick_Adapter.ViewHolder>{
    private Context context;
    private List<HandPick_Bean.IssueListBean.ItemListBean> list = new ArrayList<>();

//    private XRecylcerView recyclerView;


    private GestureDetector gestureDetector;

    private ItemOnClickListener itemOnClickListener;
    public void OnClickListener(ItemOnClickListener itemOnClickListener){
        this.itemOnClickListener=itemOnClickListener;
    }

    public HandPick_Adapter(Context context) {
        this.context = context;
    }

    public void setList(List<HandPick_Bean.IssueListBean.ItemListBean> list) {
        this.list = list;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=null;
        ViewHolder viewHolder=null;
        switch (list.get(viewType).getType()){
            case "banner1":
                view=LayoutInflater.from(context).inflate(R.layout.handpick_head_layout,parent,false);
                viewHolder=new ViewHolder(view);
                viewHolder.imageView1= (ImageView) view.findViewById(R.id.handpick_head_img_1);
                break;
            case "video":
                view= LayoutInflater.from(context).inflate(R.layout.handpick_item_1,parent,false);
                viewHolder=new ViewHolder(view);
                viewHolder.textView1= (TextView) view.findViewById(R.id.handpick_item_1_text_1);
                viewHolder.textView2= (TextView) view.findViewById(R.id.handpick_item_1_text_2);
                viewHolder.textView3= (TextView) view.findViewById(R.id.handpick_item_1_text_3);
                viewHolder.imageView1= (ImageView) view.findViewById(R.id.handpick_item_1_img_1);
                viewHolder.relativeLayout= (RelativeLayout) view.findViewById(R.id.handpick_item_1_layout_1);
                break;
            case "textHeader":
                view= LayoutInflater.from(context).inflate(R.layout.handpick_item_2,parent,false);
                viewHolder=new ViewHolder(view);
                viewHolder.textView1= (TextView) view.findViewById(R.id.handpick_item_2_text_1);
                break;
        }
       //int pos =  viewHolder.getAdapterPosition();

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        switch (list.get(position).getType()){
            case "banner1":
//                holder.imageView1
                getPicassoImageData(list.get(position).getData().getImage(),holder.imageView1,2);
                break;
            case "video":
                holder.textView1.setText(list.get(position).getData().getTitle());
                holder.textView1.setTextColor(Color.WHITE);

                int minute = 0;
                int seconds=0;
                int totlaTime=list.get(position).getData().getDuration();
                if(list.get(position).getData().getDuration()>=0)
                {
                    minute=totlaTime/60;
                    seconds=totlaTime%60;
                }
                if ((minute<10) && (seconds<10)){
                    holder.textView2.setText("#"+list.get(position).getData().getCategory()+"  /  0"+minute+"`"+"0"+seconds+"``");

                }if(minute<10&&seconds>10){
                     holder.textView2.setText("#"+list.get(position).getData().getCategory()+"  /  0"+minute+"`"+seconds+"``");
                }
                if(minute>10&&seconds<10){
                    holder.textView2.setText("#"+list.get(position).getData().getCategory()+"  /  "+minute+"`"+"0"+seconds+"``");
                }
                else {
                    holder.textView2.setText("#"+list.get(position).getData().getCategory()+"  /  "+minute+"`"+seconds+"``");
                }
                holder.textView2.setTextColor(Color.WHITE);


                if(list.get(position).getData().getAuthor()!=null){
                    holder.textView3.setVisibility(View.VISIBLE);
                    holder.textView3.setText(list.get(position).getData().getAuthor().getName());
                    holder.textView3.setTextColor(Color.WHITE);
                }
                else {
                    holder.textView3.setVisibility(View.GONE);
                }
                getPicassoImageData(list.get(position).getData().getCover().getDetail(),holder.imageView1,2);

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (itemOnClickListener != null){
                            itemOnClickListener.setOnClickListener(position);
                        }
                        imgObjAnimation(holder.imageView1);
                    }
                });
                //事件分发
                holder.itemView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        switch (motionEvent.getAction()){
                            case MotionEvent.ACTION_DOWN:
                                relativeLayoutAnimation_InVisible(holder.relativeLayout);
//                                Toast.makeText(context,"ACTION_DOWN"+position,Toast.LENGTH_SHORT).show();
                                break;
                            case MotionEvent.ACTION_UP:
                                relativeLayoutAnimation_Visible(holder.relativeLayout);
//                                Toast.makeText(context,"ACTION_UP"+position,Toast.LENGTH_SHORT).show();
                                break;
                            case MotionEvent.ACTION_MOVE:
                                //显示text内容
                                relativeLayoutAnimation_Visible(holder.relativeLayout);
//                                Toast.makeText(context,"ACTION_MOVE"+position,Toast.LENGTH_SHORT).show();
                                break;
                            case  MotionEvent.ACTION_CANCEL:
                                relativeLayoutAnimation_Visible(holder.relativeLayout);
//                                Toast.makeText(context,"ACTION_CANCEL"+position,Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return false;
                    }
                });

                break;
            case "textHeader":
                holder.textView1.setText(list.get(position).getData().getText());
                holder.textView1.setTextColor(Color.BLACK);
                AssetManager mgr=context.getAssets();
                Typeface tf=Typeface.createFromAsset(mgr,"fonts/Lobster-1.4.otf");
                holder.textView1.setTypeface(tf);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView1, textView2,textView3;
        public ImageView imageView1;
        public RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    public void clear() {
        this.list.clear();
    }

    public void addAll(List<HandPick_Bean.IssueListBean.ItemListBean> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    //用户展示的图片
    private void getPicassoImageData(String imgPath, ImageView imageView, int i) {
        //1 加载用户头像,2 加载用户展示图片
        switch (i) {
            case 1:
               /* Picasso.with(context)
                        .load(imgPath)
                        .config(Bitmap.Config.RGB_565)
                        .placeholder(R.mipmap.avator_login)//默认展示的图片
                        .error(R.mipmap.avator_login)//下载失败展示的图片
                        .resize(120,120)
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
                        .into(imageView);*/
                break;
            case 2:
                //终点,看笔记
                imageView.setAdjustViewBounds(true);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                Picasso.with(context)
                        .load(imgPath)
                        .placeholder(R.mipmap.img_back_download_error)
                        .config(Bitmap.Config.RGB_565)
                        .into(imageView);
                break;
        }
    }
    public interface ItemOnClickListener{
        void setOnClickListener(int position);
    }

    //图片放大属性动画
    private void imgObjAnimation(final ImageView imageView){
        /*ObjectAnimator animator=ObjectAnimator
                .ofFloat(imageView,"img_1",1,1.5f,1)
                .setDuration(500);
        animator.start();

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float value = (float) valueAnimator.getAnimatedValue();
                imageView.setAlpha(value);
                imageView.setScaleX(value);
                imageView.setScaleY(value);
            }
        });

        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });*/

        ScaleAnimation scaleAnimation=new ScaleAnimation(1.0f,1.5f,1.0f,1.5f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scaleAnimation.setDuration(500);

        imageView.startAnimation(scaleAnimation);

//        scaleAnimation.start();
        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
    //relative出现
    public void relativeLayoutAnimation_Visible(RelativeLayout relativeLayout){
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f,1.0f);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setDuration(500);
        relativeLayout.startAnimation(alphaAnimation);
    }
    //relative消失
    public void relativeLayoutAnimation_InVisible(RelativeLayout relativeLayout){
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f,0.0f);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setDuration(500);
        relativeLayout.startAnimation(alphaAnimation);
    }
}
