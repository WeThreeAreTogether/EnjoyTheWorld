package adapter;

import android.content.Context;
import android.graphics.Bitmap;
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

import bean.AuthorBean;
import bean.Find_QJ_Bean;

/**
 * Created by admin on 2016/7/27.
 */
public class FindQJ_LV_adapter extends BaseAdapter{
    private List<Find_QJ_Bean.ItemListBean> listBeen;
    private Context context;
    private LayoutInflater inflater;

    public FindQJ_LV_adapter(List<Find_QJ_Bean.ItemListBean> listBeen, Context context) {
        this.context = context;
        inflater=LayoutInflater.from(context);
        if(listBeen==null)
        {
            this.listBeen=new ArrayList<>();
        }else {
            this.listBeen=listBeen;
        }
    }
 public  List<Find_QJ_Bean.ItemListBean>  getData()
 {
     return  this.listBeen;
 }
    @Override
    public int getCount() {
        return listBeen==null?0:listBeen.size();
    }

    @Override
    public Object getItem(int i) {
        return listBeen.get(i);
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
            view=inflater.inflate(R.layout.find_qj_lv_item,viewGroup,false);
            viewHolder=new ViewHolder();
            viewHolder.imageView= (ImageView) view.findViewById(R.id.iv);
            viewHolder.textViewTilte= (TextView) view.findViewById(R.id.title_TV);
            viewHolder.textViewCategoty= (TextView) view.findViewById(R.id.category_tv);
            viewHolder.textViewTime= (TextView) view.findViewById(R.id.time_tv);
            viewHolder.textViewAuthor= (TextView) view.findViewById(R.id.author);
            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }
        Find_QJ_Bean.ItemListBean bean=listBeen.get(i);
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
        Find_QJ_Bean.ItemListBean.DataBean.AuthorBean authorBean=bean.getData().getAuthor();
        if(authorBean==null)
        {
            viewHolder.textViewAuthor.setVisibility(View.INVISIBLE);
        }else {
            viewHolder.textViewAuthor.setText(authorBean.getName()+"");
        }
        return view;
    }
    public class ViewHolder{
        private ImageView imageView;
        private TextView textViewTilte,textViewCategoty,textViewTime,textViewAuthor;
    }
}
