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
import com.three.enjoytheworld.FindFourActivity;
import com.three.enjoytheworld.FindSecond_TwoActivity;
import com.three.enjoytheworld.FindThreeActivity;
import com.three.enjoytheworld.R;
import com.three.enjoytheworld.ZhuanTiActivity;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
import utils.HandPick_All_Static_Obj;

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
                            if(i< HandPick_All_Static_Obj.viewPager_num)
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
                                       if(view.getTag().equals((HandPick_All_Static_Obj.viewPager_num-1)+""))
                                       {
                                           //点击ViewPager的最后一张图片的点击事件
                                           Intent intent=new Intent(getContext(), FindFourActivity.class);
                                           intent.putExtra("name","精彩探索");
                                           startActivity(intent);

                                       }else {
                                           String tag=view.getTag().toString();
                                           int id=Integer.parseInt(tag);
                                          String actionUrl=totalList.get(id).getActionUrl();
                                           Log.i("uuu", "===="+actionUrl);
                                         //  int titlePosition=actionUrl.indexOf("title=");
                                           try {
                                               //将ActionURL进行解码
                                               String path= URLDecoder.decode(actionUrl, "UTF-8");
                                               Log.i("uuu", "=======: "+path);
                                               //得到"title"在字符串中第一次出现的位置,其实是title中字符t在整个字符串中的位置
                                               int titlePosition=path.indexOf("title");
                                               int urlPosition=path.indexOf("url");
                                               String title=path.substring(titlePosition+6,urlPosition-1);
                                               String weburl=path.substring(urlPosition+4);
                                               Log.i("uuu", "==weburl=== "+weburl);
                                               Log.i("uuu", "===title===: "+title);
                                               Intent intent=new Intent(getActivity(), FindSecond_TwoActivity.class);
                                               intent.putExtra("title",title);
                                               intent.putExtra("weburl",weburl);
                                               startActivity(intent);
                                           } catch (UnsupportedEncodingException e) {
                                               e.printStackTrace();
                                           }
                                       }
                                    }
                                });

                            }else if(i==HandPick_All_Static_Obj.viewPager_num)
                            {
                                loadImage(totalList.get(i).getImage(),imageView_01);
                            }
                            else if(i==HandPick_All_Static_Obj.viewPager_num+1)
                            {
                                loadImage(totalList.get(i).getImage(),imageView_02);
                            }else if(i==HandPick_All_Static_Obj.viewPager_num+2)
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
