package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.squareup.picasso.Picasso;
import com.three.enjoytheworld.R;

import java.util.List;

import bean.FindLVBean;


/**
 * Created by admin on 2016/7/26.
 */
public class FindVP_LV_Adapter extends BaseAdapter {
    private Context context;
    private List<FindLVBean> list;
    private LayoutInflater inflater;
    public FindVP_LV_Adapter(Context context, List<FindLVBean> list) {
        this.context = context;
        this.list = list;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
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
        ViewHolder viewHolder;
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
            viewHolder.imageViewTotalBackground= (LinearLayout) view.findViewById(R.id.linearlayout);
            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }
        FindLVBean lvBean=list.get(i);
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

        return view;
    }
    public class ViewHolder{
        private TextView textViewTitle,textViewCategory,textViewTime,textViewDetail;
        private VideoView vitamio;
        private ImageView imageViewBackground,imageViewStart;
        private LinearLayout imageViewTotalBackground;
    }
    public void loadImage(String path,ImageView imageView)
    {
        Picasso.with(context).load(path)
                              .placeholder(R.mipmap.ic_eye_black_error)
                              .error(R.mipmap.ic_launcher)
                              .into(imageView);
    }
}
