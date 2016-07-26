package adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.three.enjoytheworld.R;

import java.util.ArrayList;
import java.util.List;

import bean.HandPick_Bean;

/**
 * Created by Sadewangzi on 2016/7/26.
 */
public class HandPick_Adapter extends RecyclerView.Adapter<HandPick_Adapter.ViewHolder>{
    private Context context;
    private List<HandPick_Bean.IssueListBean.ItemListBean> list=new ArrayList<>();

    public HandPick_Adapter(Context context) {
        this.context = context;
    }

    public void setList(List<HandPick_Bean.IssueListBean.ItemListBean> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list!=null?list.size():0;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView1, textView2;
        public ImageView imageView1, imageView2;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
    public void clear(){
        this.list.clear();
    }
    public void addAll(List<HandPick_Bean.IssueListBean.ItemListBean> list){
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
                Picasso.with(context)
                        .load(imgPath)
                        .centerCrop()
                        .placeholder(R.mipmap.img_back_download_error)
                        .config(Bitmap.Config.RGB_565)
                        .resize(context.getResources().getDisplayMetrics().widthPixels + 60, 400)
                        .into(imageView);
                break;
        }
    }
}
