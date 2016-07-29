package adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kogitune.activity_transition.ActivityTransitionLauncher;
import com.squareup.picasso.Picasso;
import com.three.enjoytheworld.AuthorDetailActivity;
import com.three.enjoytheworld.R;

import java.util.ArrayList;
import java.util.List;

import bean.AuthorItemBean;

/**
 * Created by YanTi on 2016/7/27.
 */
public class AuthorItemAdapter extends RecyclerView.Adapter<AuthorItemAdapter.ViewHolder> {

    private static final String TAG = AuthorItemAdapter.class.getSimpleName();
    private Context mContext;
    private List<AuthorItemBean.ItemListBean> data = new ArrayList<>();



    public AuthorItemAdapter(Context context, List<AuthorItemBean.ItemListBean> data) {

        mContext = context;
        if (data != null) {
            this.data = data;
        }
    }

    public void setData(List<AuthorItemBean.ItemListBean> data) {
        if (data != null) {
            this.data = data;
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.author_item_detail, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        //设置图片的点击事件，实现共享视图跳转到另一个Activity
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, AuthorDetailActivity.class);
                intent.putExtra("flag",1);
                Bundle bundle = new Bundle();
                bundle.putSerializable("data1",data.get(position).getData());
                intent.putExtras(bundle);
                ActivityTransitionLauncher.with((Activity) mContext).from(holder.iv_cover).launch(intent);
            }
        });
        AuthorItemBean.ItemListBean bean = data.get(position);
        if (bean != null) {
            AuthorItemBean.ItemListBean.DataBean data1 = bean.getData();
            if (data1 != null) {

                String feed = data1.getCover().getFeed();
                if (!TextUtils.isEmpty(feed)){
                    Picasso.with(mContext).load(feed).into(holder.iv_cover);
                }
                String title = data1.getTitle();
                if (!TextUtils.isEmpty(title)){
                    holder.tv_title.setText(title);
                }

                String category = data1.getCategory();
                if (!TextUtils.isEmpty(category)){
                    holder.tv_category.setText("#"+category);
                }

                int duration = data1.getDuration();
                if (duration != 0){
                    int minute = duration/60;
                    int second = duration%60;
                    holder.tv_duration.setText(minute+"'"+second+"\"");
                }

                AuthorItemBean.ItemListBean.DataBean.AuthorBean author = data1.getAuthor();
                if (author != null) {
                    String name = author.getName();
                    if (!TextUtils.isEmpty(name)){
                        holder.tv_name.setText(name);
                    }
                }

            }
        }
    }

    @Override
    public int getItemCount() {
        return data!=null ?data.size():0;
    }



    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_title,tv_category,tv_duration,tv_name;
        private ImageView iv_cover;
        public ViewHolder(View itemView) {
            super(itemView);

            tv_title = ((TextView) itemView.findViewById(R.id.tv_author_title));
            tv_category = ((TextView) itemView.findViewById(R.id.tv_author_category));
            tv_duration = ((TextView) itemView.findViewById(R.id.tv_author_duration));
            tv_name = ((TextView) itemView.findViewById(R.id.tv_author_name));

            iv_cover = ((ImageView) itemView.findViewById(R.id.iv_author_cover));
        }
    }

}
