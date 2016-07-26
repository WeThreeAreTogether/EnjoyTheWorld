package adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
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
public class HandPick_Adapter extends RecyclerView.Adapter<HandPick_Adapter.ViewHolder> {
    private Context context;
    private List<HandPick_Bean.IssueListBean.ItemListBean> list = new ArrayList<>();

    public HandPick_Adapter(Context context) {
        this.context = context;
    }

    public void setList(List<HandPick_Bean.IssueListBean.ItemListBean> list) {
        this.list = list;
        Log.e("0000000000000000000000", "setList: "+list.size() );
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=null;
        ViewHolder viewHolder=null;
        /*Log.e("wwwwwwww", "onCreateViewHolder:111111111111111111111111111111 ");
        Log.e("wwwwwwww", "onCreateViewHolder: "+list.get(viewType).getType() );
        Log.e("wwwwwwww", "onCreateViewHolder:222222222222222222222222222222 ");*/

        switch (list.get(viewType).getType()){
            case "video":
//                Log.e("wwwwwwww", "onCreateViewHolder: " );
                view= LayoutInflater.from(context).inflate(R.layout.handpick_item_1,parent,false);
                viewHolder=new ViewHolder(view);
                viewHolder.textView1= (TextView) view.findViewById(R.id.handpick_item_1_text_1);
                viewHolder.textView2= (TextView) view.findViewById(R.id.handpick_item_1_text_2);
                viewHolder.textView3= (TextView) view.findViewById(R.id.handpick_item_1_text_3);
                viewHolder.imageView1= (ImageView) view.findViewById(R.id.handpick_item_1_img_1);
                break;
            case "textHeader":
//                Log.e("qqqqqqqq", "onCreateViewHolder: " );
                view= LayoutInflater.from(context).inflate(R.layout.handpick_item_2,parent,false);
                viewHolder=new ViewHolder(view);
                viewHolder.textView1= (TextView) view.findViewById(R.id.handpick_item_2_text_1);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        switch (list.get(position).getType()){
            case "video":
                holder.textView1.setText(list.get(position).getData().getTitle());
                holder.textView1.setTextColor(Color.WHITE);

                holder.textView2.setText(list.get(position).getData().getCategory());
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
