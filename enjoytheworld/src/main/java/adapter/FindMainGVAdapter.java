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

import java.util.List;

import bean.FindMainItem;

/**
 * Created by admin on 2016/7/26.
 */
public class FindMainGVAdapter extends BaseAdapter {
    private Context context;
    private List<FindMainItem> list;
    private LayoutInflater inflater;

    public FindMainGVAdapter(Context context, List<FindMainItem> list) {
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
            view=inflater.inflate(R.layout.findmain_two,viewGroup,false);
            viewHolder=new ViewHolder();
            viewHolder.textView= (TextView) view.findViewById(R.id.tv);
            viewHolder.imageView= (ImageView) view.findViewById(R.id.iv);
            view.setTag(viewHolder);

        }else
        {
            viewHolder= (ViewHolder) view.getTag();
        }
        FindMainItem mainItem=list.get(i);
        String title=mainItem.getTltle();
        String imagePath=mainItem.getImage();
            viewHolder.textView.setText(title+"");
        Picasso.with(context).load(imagePath)
                              .placeholder(R.mipmap.ic_eye_black_error)
                              .error(R.mipmap.ic_eye_black_error)
                              .config(Bitmap.Config.RGB_565)
                              .into(viewHolder.imageView);

        return view;
    }
    public class ViewHolder{
        private TextView textView;
        private ImageView imageView;
    }
}
