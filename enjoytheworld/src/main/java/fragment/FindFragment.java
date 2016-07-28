package fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.three.enjoytheworld.FindSecondAcivity;
import com.three.enjoytheworld.FindThreeActivity;
import com.three.enjoytheworld.R;
import com.three.enjoytheworld.ZhuanTiActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import CustomView.MyGrideView;
import adapter.FindMainGVAdapter;
import adapter.FindViewPagerAdapter;
import bean.FindMainItem;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import uri.FindUri;
import utils.FindJson;

/**
 * A simple {@link Fragment} subclass.
 */
public class FindFragment extends Fragment {
   private View view;
    private OkHttpClient okHttpClient;
    private Request request;
    private Call call;
    private ViewPager viewPager;
    private ImageView imageView_01,imageView_02,imageView_03;
    private MyGrideView grideView;
    private List<FindMainItem> totalList;
    private List<ImageView> viewPagerList=new ArrayList<>();
    private List<FindMainItem> grideViewList=new ArrayList<>();
    private FindMainGVAdapter adapter;
    private FindViewPagerAdapter viewPagerAdapter;
    private int tag=0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_find, container, false);
        //初始化控件
        initView();
        //加载数据
        loadData(FindUri.FindMain);
        //设置点击事件
        setListener();
        return view;
    }

    private void loadData(String path) {
        okHttpClient=new OkHttpClient();
        request=new Request.Builder()
                             .url(path)
                            .build();
        call=okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                    String json=response.body().string();
                   totalList=FindJson.parseJson(json);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < totalList.size(); i++) {
                            if(i==0||i==1)
                            {
                                //加载ViewPager的数据
                                ImageView imageView=new ImageView(getContext());
                                Log.i("FFF", "========run: "+totalList.get(i).getImage());
                                loadImage(totalList.get(i).getImage(),imageView);
                                viewPagerList.add(imageView);
                                viewPagerAdapter =new FindViewPagerAdapter(viewPagerList);
                                viewPager.setAdapter(viewPagerAdapter);
                                imageView.setTag(i+"");
                                //设置viewPager中图片的点击事件
                                imageView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                       if(view.getTag().equals("0"))
                                       {
                                           Log.i("nnn", "==========: "+"点击了ViewPager的第一张图片");
                                           Intent intent=new Intent(getContext(), FindSecondAcivity.class);
                                           intent.putExtra("title","超级拼的领导人");
                                           startActivity(intent);

                                       }else {
                                           //点击ViewPager的第二个图片的点击事件
                                       }
                                    }
                                });

                            }else if(i==2)
                            {
                                loadImage(totalList.get(i).getImage(),imageView_01);
                            }
                            else if(i==3)
                            {
                                loadImage(totalList.get(i).getImage(),imageView_02);
                            }else if(i==4)
                            {
                                loadImage(totalList.get(i).getImage(),imageView_03);

                            }else
                            {
                                grideViewList.add(totalList.get(i));
                            }
                        }
                        adapter=new FindMainGVAdapter(getContext(),grideViewList);
                        grideView.setAdapter(adapter);
                    }
                });

            }
        });
    }

    private void initView() {
        viewPager= (ViewPager) view.findViewById(R.id.viewpager);
        imageView_01= (ImageView) view.findViewById(R.id.iv_01);
        imageView_02= (ImageView) view.findViewById(R.id.iv_02);
        imageView_03= (ImageView) view.findViewById(R.id.iv_03);
        grideView= (MyGrideView) view.findViewById(R.id.grideView);
    }
 public void loadImage(String path,ImageView imageView)
 {
     Picasso.with(getContext())
             .load(path)
             .placeholder(R.mipmap.ic_eye_black_error)
             .error(R.mipmap.ic_eye_black_error)
             .config(Bitmap.Config.RGB_565)
             .into(imageView);
 }
//设置监听事件
    public void setListener()
    {
        //点击最受欢迎
        imageView_01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), FindThreeActivity.class);
                intent.putExtra("name","最受欢迎");
                startActivity(intent);
            }
        });
        //点击热门专题
        imageView_02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             Intent intent=new Intent(getContext(),ZhuanTiActivity.class);
                intent.putExtra("name","热门专题");
                startActivity(intent);
            }
        });
        //点击360全景
        imageView_03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), FindThreeActivity.class);
                intent.putExtra("name","360度全景");
                startActivity(intent);
            }
        });
        grideView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext(),"点击了第"+i+"个条目",Toast.LENGTH_LONG).show();
                String id=grideViewList.get(i).getId();
                int idInt=Integer.parseInt(id);
                String name=grideViewList.get(i).getTltle();
                Intent intent=new Intent(getContext(),FindThreeActivity.class);
                intent.putExtra("name",name);
                intent.putExtra("id",idInt);
                startActivity(intent);

            }
        });
    }
}
