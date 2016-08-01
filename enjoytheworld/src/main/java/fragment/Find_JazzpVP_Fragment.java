package fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.three.enjoytheworld.HandPick_Vitamio;
import com.three.enjoytheworld.R;

import bean.FindPopular_LVBean;

/**
 * Created by admin on 2016/7/29.
 */
public class Find_JazzpVP_Fragment extends Fragment{
    private View view;
    private ImageView imageViewBackgound,imageViewDetail;
    private TextView textViewTitle,textViewCategory,textViewTime,textViewAuthor,textViewDetail;
    private FindPopular_LVBean.ItemListBean.DataBean dataBean;
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.find_jazzpfragment,container,false);
        initView();
        Bundle bundle=getArguments();
        dataBean= (FindPopular_LVBean.ItemListBean.DataBean) bundle.getSerializable("data");
        Log.i("iii", "onCreateView: "+dataBean.toString());

        textViewTitle.setText(dataBean.getTitle()+"");
        textViewCategory.setText(dataBean.getCategory()+"");
        int totalTime=dataBean.getDuration();
        if(totalTime!=0)
        {
            int minute=totalTime/60;
            int seconds=totalTime%60;
            Log.i("iiii", "===textViewTime====: "+totalTime);
            textViewTime.setText(minute+"'"+seconds+"\"");
        }
        FindPopular_LVBean.ItemListBean.DataBean.AuthorBean authorBean=dataBean.getAuthor();
        if(authorBean==null)
        {
            textViewAuthor.setVisibility(View.INVISIBLE);
        }else {
            textViewAuthor.setText(authorBean.getName());
        }
        textViewDetail.setText(dataBean.getDescription());
        loadImage(dataBean.getCover().getBlurred(),imageViewBackgound);
        Log.i("iii", "======"+dataBean.getCover().getBlurred());
        loadImage(dataBean.getCover().getDetail(),imageViewDetail);
        imageViewDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), HandPick_Vitamio.class);
                intent.putExtra("url",dataBean.getPlayUrl());
                startActivity(intent);
            }
        });
        return view;
    }

    private void initView() {
        imageViewBackgound= (ImageView) view.findViewById(R.id.viewpager_backgound);
        imageViewDetail= (ImageView) view.findViewById(R.id.iv);
        textViewTitle= (TextView) view.findViewById(R.id.title_TV);
        textViewCategory= (TextView) view.findViewById(R.id.category_tv);
        textViewTime= (TextView) view.findViewById(R.id.time_tv);
        textViewAuthor= (TextView) view.findViewById(R.id.author);
        textViewDetail= (TextView) view.findViewById(R.id.detail);
    }
    public void loadImage(String path,ImageView imageView)
    {
        Picasso.with(getContext()).load(path)
                                   .placeholder(R.mipmap.ic_eye_black_error)
                                   .error(R.mipmap.ic_eye_black_error)
                                    .config(Bitmap.Config.RGB_565)
                                   .into(imageView);
    }
}
