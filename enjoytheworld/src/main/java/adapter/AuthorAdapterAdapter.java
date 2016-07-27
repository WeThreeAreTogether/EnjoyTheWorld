package adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.three.enjoytheworld.AuthorDetailActivity;
import com.three.enjoytheworld.R;

import java.util.ArrayList;
import java.util.List;

import bean.AuthorBean;

/**
 * Created by YanTi on 2016/7/26.
 */
public class AuthorAdapterAdapter extends RecyclerView.Adapter<AuthorAdapterAdapter.ViewHolder> {


    private Context mContext;
    private List<AuthorBean.ItemListBean.DataBean.ItemList2Bean> data = new ArrayList<>();

    private OnItemDetailClickListen mOnItemDetailClickListen;

    public void setOnItemDetailClickListen(OnItemDetailClickListen onItemDetailClickListen) {
        mOnItemDetailClickListen = onItemDetailClickListen;
    }

    public AuthorAdapterAdapter(Context context, List<AuthorBean.ItemListBean.DataBean.ItemList2Bean> data) {

        mContext = context;
        if (data != null) {
            this.data = data;
        }
    }


    public interface OnItemDetailClickListen{
        void onItemDetailClickListen(int position);
    }

    @Override
    public int getItemCount() {
        return data!=null ? data.size():0;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.author_item20, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(mContext, AuthorDetailActivity.class));
            }
        });
        AuthorBean.ItemListBean.DataBean.ItemList2Bean bean = data.get(position);
        AuthorBean.ItemListBean.DataBean.ItemList2Bean.Data2Bean data1 = bean.getData();
        if (bean != null) {

            if (data1 != null) {
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
                    holder.tv_duration.setText(String.valueOf(minute)+"'"+String.valueOf(second)+"\"");

                }

                AuthorBean.ItemListBean.DataBean.ItemList2Bean.Data2Bean.CoverBean cover = data1.getCover();
                if (cover != null) {
                    String feed = cover.getFeed();
                    if (!TextUtils.isEmpty(feed)){
                        Picasso.with(mContext).load(feed).into(holder.iv_cover);
                    }
                }


            }
        }

    }



    class ViewHolder extends RecyclerView.ViewHolder{


        private TextView tv_title,tv_category,tv_duration;
        private ImageView iv_cover;

        public ViewHolder(View itemView) {
            super(itemView);

            iv_cover = ((ImageView) itemView.findViewById(R.id.iv_author_cover));

            tv_title = ((TextView) itemView.findViewById(R.id.tv_author_title));
            tv_category = ((TextView) itemView.findViewById(R.id.tv_author_title_category));
            tv_duration = ((TextView) itemView.findViewById(R.id.tv_author_title_duration));
        }
    }
}
