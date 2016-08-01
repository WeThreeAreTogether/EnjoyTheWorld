package adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kogitune.activity_transition.ActivityTransitionLauncher;
import com.squareup.picasso.Picasso;
import com.three.enjoytheworld.FindFiveActivity;
import com.three.enjoytheworld.FindThreeActivity;
import com.three.enjoytheworld.R;

import java.util.ArrayList;
import java.util.List;

import bean.FindPopular_LVBean;

/**
 * Created by admin on 2016/7/27.
 */
public class FindPopular_LV_Adapter extends BaseAdapter {
    private List<FindPopular_LVBean.ItemListBean> findPopular_lvBeenList;
    private Context context;
    private LayoutInflater inflater;

    public FindPopular_LV_Adapter(List<FindPopular_LVBean.ItemListBean> findPopular_lvBeenList, Context context) {
        this.context = context;
        inflater=LayoutInflater.from(context);
        if(findPopular_lvBeenList==null)
        {
            this.findPopular_lvBeenList=new ArrayList<>();
        }else {
            this.findPopular_lvBeenList=findPopular_lvBeenList;
        }
    }

    @Override
    public int getCount() {
        return findPopular_lvBeenList==null?0:findPopular_lvBeenList.size();
    }

    @Override
    public Object getItem(int i) {
        return findPopular_lvBeenList.get(i);
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
            view=inflater.inflate(R.layout.top_popular_item,viewGroup,false);
            viewHolder=new ViewHolder();
            viewHolder.imageView= (ImageView) view.findViewById(R.id.iv);
            viewHolder.textViewTilte= (TextView) view.findViewById(R.id.title_TV);
            viewHolder.textViewCategoty= (TextView) view.findViewById(R.id.category);
            viewHolder.textViewTime= (TextView) view.findViewById(R.id.time_tv);
            viewHolder.textViewOrder= (TextView) view.findViewById(R.id.order);
            view.setTag(viewHolder);
        }
        else {
            viewHolder= (ViewHolder) view.getTag();
        }
       FindPopular_LVBean.ItemListBean bean=findPopular_lvBeenList.get(i);
        Picasso.with(context).load(bean.getData().getCover().getDetail())
                              .placeholder(R.mipmap.ic_eye_black_error)
                              .error(R.mipmap.ic_eye_black_error)
                              .config(Bitmap.Config.RGB_565)
                              .into(viewHolder.imageView);
        viewHolder.textViewTilte.setText(bean.getData().getTitle()+"");
        viewHolder.textViewCategoty.setText(bean.getData().getCategory()+"");
        int totlaTime=bean.getData().getDuration();
        if(totlaTime>=0)
        {
            int minute=totlaTime/60;
            int seconds=totlaTime%60;
            viewHolder.textViewTime.setText(minute+"'"+seconds+"\"");
        }
        viewHolder.textViewOrder.setText((i+1)+"");

        return view;
    }
    public class ViewHolder{
        private ImageView imageView;
        private TextView textViewTilte,textViewCategoty,textViewTime,textViewOrder;
    }
}
