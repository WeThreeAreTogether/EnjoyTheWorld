package adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.three.enjoytheworld.R;

import java.util.ArrayList;
import java.util.List;

import bean.FindZhuanTiBean;

/**
 * Created by admin on 2016/7/27.
 */
public class FindZhuanTi_LV_Adapteer extends BaseAdapter{
    private List<FindZhuanTiBean.ItemListBean> listBeen;
    private Context context;
    private LayoutInflater inflater;

    public FindZhuanTi_LV_Adapteer(Context context, List<FindZhuanTiBean.ItemListBean> listBeen) {
        this.context = context;
        this.inflater=LayoutInflater.from(context);
        if(listBeen==null)
        {
            this.listBeen=new ArrayList<>();
        }else {
            this.listBeen=listBeen;
        }
    }

    @Override
    public int getCount() {
        return this.listBeen==null?0:listBeen.size();
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
            view=inflater.inflate(R.layout.find_zt_lv_item,viewGroup,false);
            viewHolder=new ViewHolder();
            viewHolder.imageView= (ImageView) view.findViewById(R.id.iv);
            view.setTag(viewHolder);
        }
        else {
            viewHolder= (ViewHolder) view.getTag();
        }
        FindZhuanTiBean.ItemListBean bean=listBeen.get(i);
        String imagePath=bean.getData().getImage();
        Picasso.with(context).load(imagePath)
                              .placeholder(R.mipmap.ic_eye_black_error)
                              .error(R.mipmap.ic_eye_black_error)
                              .config(Bitmap.Config.RGB_565)
                              .into(viewHolder.imageView);
        return view;
    }
    public class ViewHolder{
        private ImageView imageView;
    }
}
