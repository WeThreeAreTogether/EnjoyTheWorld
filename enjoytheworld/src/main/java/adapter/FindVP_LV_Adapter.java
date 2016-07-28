package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.three.enjoytheworld.R;

import java.util.ArrayList;
import java.util.List;

import bean.FindLVBean;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;


/**
 * Created by admin on 2016/7/26.
 */
public class FindVP_LV_Adapter extends BaseAdapter {
    private Context context;
    private List<FindLVBean> list;
    private LayoutInflater inflater;
    public FindVP_LV_Adapter(Context context, List<FindLVBean> list) {
        this.context = context;

        inflater=LayoutInflater.from(context);
        if(list==null)
        {
            this.list=new ArrayList<>();
        }else{
            this.list=list;
        }
    }

    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        if(view==null)
        {
            view=inflater.inflate(R.layout.findlistview_oneitem,viewGroup,false);
            viewHolder=new ViewHolder();
            viewHolder.textViewTitle= (TextView) view.findViewById(R.id.tv_Title);
            viewHolder.textViewCategory= (TextView) view.findViewById(R.id.category);
            viewHolder.textViewTime= (TextView) view.findViewById(R.id.showTime);
            viewHolder.textViewDetail= (TextView) view.findViewById(R.id.detail);
            viewHolder.vitamio= (VideoView) view.findViewById(R.id.video);
            viewHolder.imageViewBackground= (ImageView) view.findViewById(R.id.video_iv);
            viewHolder.imageViewStart= (ImageView) view.findViewById(R.id.video_start);
            viewHolder.imageViewTotalBackground= (ImageView) view.findViewById(R.id.background);
            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }
        final FindLVBean lvBean=list.get(i);
        viewHolder.textViewTitle.setText(lvBean.getTitle()+"");
        viewHolder.textViewCategory.setText(lvBean.getCategory()+"");
        viewHolder.textViewDetail.setText(lvBean.getDescription()+"");
        int totlaTime=lvBean.getDuration();
        if(totlaTime!=0)
        {
            int minute=totlaTime/60;
            int seconds=totlaTime%60;
            viewHolder.textViewTime.setText(minute+"'"+seconds+"\"");
        }
      //加载背景图片
        loadImage(lvBean.getCoverBlurred(),viewHolder.imageViewTotalBackground);




        //加载vitmio上的背景图片
        loadImage(lvBean.getCoverForDetail(),viewHolder.imageViewBackground);
        //点击图片,图片消失,视频开始播放
        viewHolder.imageViewBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewHolder.imageViewBackground.setVisibility(View.GONE);
                viewHolder.imageViewStart.setVisibility(View.GONE);
                //加载vitmio
                Vitamio.isInitialized(context);
                viewHolder.vitamio.setVideoPath(lvBean.getPlayUrl());
                viewHolder.vitamio.setMediaController(new MediaController(context));
                viewHolder.vitamio.start();
            }
        });
        //点击开始图标后开始播放视频
        viewHolder.imageViewStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewHolder.imageViewBackground.setVisibility(View.GONE);
                viewHolder.imageViewStart.setVisibility(View.GONE);
                //加载vitmio
                Vitamio.isInitialized(context);
                viewHolder.vitamio.setVideoPath(lvBean.getPlayUrl());
                viewHolder.vitamio.setMediaController(new MediaController(context));
                viewHolder.vitamio.start();
            }
        });
       //视频播放完成,图片展示
        viewHolder.vitamio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                viewHolder.imageViewBackground.setVisibility(View.VISIBLE);
                viewHolder.imageViewStart.setVisibility(View.VISIBLE);
            }
        });
        return view;
    }
    public class ViewHolder{
        private TextView textViewTitle,textViewCategory,textViewTime,textViewDetail;
        private VideoView vitamio;
        private ImageView imageViewBackground,imageViewStart,imageViewTotalBackground;
       // private LinearLayout imageViewTotalBackground;
    }
    public void loadImage(String path,ImageView imageView)
    {
        Picasso.with(context).load(path)
                              .placeholder(R.mipmap.ic_eye_black_error)
                              .error(R.mipmap.ic_launcher)
                              .into(imageView);
    }
}
