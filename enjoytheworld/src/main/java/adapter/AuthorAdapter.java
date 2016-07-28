package adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.three.enjoytheworld.R;

import java.util.ArrayList;
import java.util.List;

import bean.AuthorBean;

/**
 * Created by YanTi on 2016/7/25.
 */
public class AuthorAdapter extends RecyclerView.Adapter<AuthorAdapter.ViewHolder> implements Transformation{


    private Context mContext;
    private List<AuthorBean.ItemListBean> data = new ArrayList<>();
    private LayoutInflater mInflater;


    private OnItemClickListen mOnItemClickListen;

    public void setOnItemClickListen(OnItemClickListen onItemClickListen) {
        mOnItemClickListen = onItemClickListen;
    }

    public AuthorAdapter(Context context, List<AuthorBean.ItemListBean> data) {

        this.mContext = context;
        mInflater = LayoutInflater.from(context);

        if (data != null) {
            this.data = data;
        }

    }

    public void setData(List<AuthorBean.ItemListBean> data) {
        if (data != null) {
            this.data.addAll(data);
            notifyDataSetChanged();
        }

    }

    public List<AuthorBean.ItemListBean> getData() {
        return data;
    }

    public interface OnItemClickListen{

        void onItemClick(int position);
    }
    @Override
    public int getItemCount() {
        return data!=null ? data.size():0;
    }



    @Override
    public int getItemViewType(int position) {

        //默认第一种item
        int viewType = 0;
        String type = data.get(position).getType();
        if ("leftAlignTextHeader".equals(type)) {
            viewType = 0;
        }else if ("briefCard".equals(type)){
            viewType = 1;
        }else if("videoCollectionWithBrief".equals(type)){
            viewType = 2;
        }else if ("blankCard".equals(type)){
            viewType = 3;
        }

        return viewType;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = null;
        if(viewType == 0){
            view = mInflater.inflate(R.layout.author_item0,parent,false);

        }else if(viewType == 1){
            view = mInflater.inflate(R.layout.author_item1,parent,false);

        }else if (viewType == 2){
            view = mInflater.inflate(R.layout.author_item2,parent,false);

        }else {
            view = mInflater.inflate(R.layout.author_item_blank,parent,false);
        }



        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListen != null) {
                    mOnItemClickListen.onItemClick(position);
                }
            }
        });
        AuthorBean.ItemListBean itemListBean = data.get(position);

        String type = itemListBean.getType();

        if ("leftAlignTextHeader".equals(type)) {
            //第一种布局
            String text = itemListBean.getData().getText();
            if (!TextUtils.isEmpty(text)){
                holder.tv_text.setText(text);
            }

        }else if ("briefCard".equals(type)){
            //第二种布局
            AuthorBean.ItemListBean.DataBean data1 = itemListBean.getData();
            if (data1 != null){
                String icon = data1.getIcon();
                if (!TextUtils.isEmpty(icon)){
                    Picasso.with(mContext)
                            .load(icon)
                            .error(R.mipmap.ic_tab_strip_icon_pgc)
                            .placeholder(R.mipmap.ic_tab_strip_icon_pgc)
                            .transform(this)
                            .into(holder.iv_icon);
                }

                String title = data1.getTitle();
                if (! TextUtils.isEmpty(title)){
                    holder.tv_title.setText(title);
                }
                String subTitle = data1.getSubTitle();
                if (! TextUtils.isEmpty(subTitle)){
                    holder.tv_subTitle.setText(subTitle);
                }
                String description = data1.getDescription();
                if (! TextUtils.isEmpty(description)){
                    holder.tv_description.setText(description);
                }

            }
        }else if("videoCollectionWithBrief".equals(type)){
            //第三种布局

            AuthorBean.ItemListBean.DataBean data2 = itemListBean.getData();
            if (data2 != null) {
                AuthorBean.ItemListBean.DataBean.HeaderBean header = data2.getHeader();
                if (header != null) {
                    String icon = header.getIcon();
                    if (!TextUtils.isEmpty(icon)){
                        Picasso.with(mContext)
                                .load(icon)
                                .placeholder(R.mipmap.ic_tab_strip_icon_pgc)
                                .error(R.mipmap.ic_tab_strip_icon_pgc)
                                .transform(this)
                                .into(holder.iv_icon);
                    }
                    String title = header.getTitle();
                    if (!TextUtils.isEmpty(title)){
                        holder.tv_title.setText(title);
                    }
                    String subTitle = header.getSubTitle();
                    if (!TextUtils.isEmpty(subTitle)){
                        holder.tv_subTitle.setText(subTitle);

                    }
                    String description = header.getDescription();
                    if (!TextUtils.isEmpty(description)){
                        holder.tv_description.setText(description);
                    }
                }
                List<AuthorBean.ItemListBean.DataBean.ItemList2Bean> itemList = data2.getItemList();
                if (itemList != null) {

                    LinearLayoutManager manager = new LinearLayoutManager(mContext);
                    manager.setOrientation(LinearLayoutManager.HORIZONTAL);
                    holder.mRecyclerView.setLayoutManager(manager);
                    holder.mRecyclerView.setAdapter(new AuthorAdapterAdapter(mContext,itemList));
                }
            }

        }


    }

    @Override
    public Bitmap transform(Bitmap source) {

        Bitmap bitmap = toRoundBitmap(source);
        if (bitmap != source){
            source.recycle();
        }
        return bitmap;
    }

    @Override
    public String key() {
        return null;
    }


    class ViewHolder extends RecyclerView.ViewHolder{

        private final RecyclerView mRecyclerView;
        private ImageView iv_icon;
        private TextView tv_text,tv_title,tv_subTitle,tv_description;

        public ViewHolder(View itemView) {
            super(itemView);

            tv_text = (TextView) itemView.findViewById(R.id.tv_author_text);
            tv_title = (TextView) itemView.findViewById(R.id.tv_author_title);
            tv_subTitle = (TextView) itemView.findViewById(R.id.tv_author_subtitle);
            tv_description = (TextView) itemView.findViewById(R.id.tv_author_description);

            iv_icon = ((ImageView) itemView.findViewById(R.id.iv_author_icon));
            mRecyclerView = ((RecyclerView) itemView.findViewById(R.id.recyclerView_item2));
        }
    }

    /**
     * 将图片变为圆形
     * @param bitmap
     * @return
     */
    public Bitmap toRoundBitmap(Bitmap bitmap) {
        //圆形图片宽高
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        //正方形的边长
        int r = 0;
        //取最短边做边长
        if(width > height) {
            r = height;
        } else {
            r = width;
        }
        //构建一个bitmap
        Bitmap backgroundBmp = Bitmap.createBitmap(width,
                height, Bitmap.Config.ARGB_8888);
        //new一个Canvas，在backgroundBmp上画图
        Canvas canvas = new Canvas(backgroundBmp);
        Paint paint = new Paint();
        //设置边缘光滑，去掉锯齿
        paint.setAntiAlias(true);
        //宽高相等，即正方形
        RectF rect = new RectF(0, 0, r, r);
        //通过制定的rect画一个圆角矩形，当圆角X轴方向的半径等于Y轴方向的半径时，
        //且都等于r/2时，画出来的圆角矩形就是圆形
        canvas.drawRoundRect(rect, r/2, r/2, paint);
        //设置当两个图形相交时的模式，SRC_IN为取SRC图形相交的部分，多余的将被去掉
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        //canvas将bitmap画在backgroundBmp上
        canvas.drawBitmap(bitmap, null, rect, paint);
        //返回已经绘画好的backgroundBmp
        return backgroundBmp;
    }

}
