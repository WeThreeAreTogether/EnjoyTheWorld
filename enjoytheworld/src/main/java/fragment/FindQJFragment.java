package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.kogitune.activity_transition.ActivityTransitionLauncher;
import com.three.enjoytheworld.FindFiveActivity;
import com.three.enjoytheworld.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import adapter.FindPopular_LV_Adapter;
import adapter.FindQJ_LV_adapter;
import bean.FindPopular_LVBean;
import bean.Find_QJ_Bean;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import uri.FindUri;
import utils.HandPick_All_Static_Obj;

/**
 * Created by admin on 2016/7/27.
 */
public class FindQJFragment extends Fragment {
    private ListView listView;
    private View view;
    private FindQJ_LV_adapter adapter;
    private OkHttpClient okHttpClient;
    private Request request;
    private Call call;
    private Find_QJ_Bean qj_bean;
    private List<Find_QJ_Bean.ItemListBean> listBeen=new ArrayList<>();
    private boolean flag=false;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.find_popular_listview,container,false);
        listView= (ListView) view.findViewById(R.id.listview);

        Bundle bundle=getArguments();
        int id=bundle.getInt("id");
        adapter=new FindQJ_LV_adapter(listBeen,getContext());
        listView.setAdapter(adapter);
        switch (bundle.getInt("flag")) {
            case 0:
                //按时间排序
                if(id==-1)
                {

                    loadData(String.format(FindUri.QuanJing,"date"));
                }else {
                    String idstr=String.valueOf(id);
                    loadData(String.format(FindUri.WangGe,idstr,"date"));
                }

                break;
            case 1:
                //按分享排序
                if(id==-1)
                {

                    loadData(String.format(FindUri.QuanJing,"shareCount"));
                }else {
                    String idstr=String.valueOf(id);
                    loadData(String.format(FindUri.WangGe,idstr,"shareCount"));
                }

                break;

        }
        //listview的加载更多的监听
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                if(flag==true && scrollState==0)
                {
                    String nextPageUrl=qj_bean.getNextPageUrl();
                    if(nextPageUrl!=null)
                    {
                        Toast.makeText(getContext(),"加载下一页",Toast.LENGTH_LONG).show();
                        loadData(nextPageUrl);
                    }else {
                        Toast.makeText(getContext(),"没有更多内容啦",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(firstVisibleItem+visibleItemCount==totalItemCount)
                {
                    flag=true;
                }else {
                    flag=false;
                }
            }
        });
        //listview的点击监听事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getActivity(),FindFiveActivity.class);
                Bundle bundle=new Bundle();
               // bundle.putSerializable("data",listBeen.get(i).getData());
                bundle.putSerializable("data",adapter.getData().get(i).getData());
                intent.putExtras(bundle);
                intent.putExtra("flag",1);
                //获取点击的listview的item上的图片
                ImageView imageView= (ImageView) view.findViewById(R.id.iv);
                //获取ImageView上的图片,并保存到全局变量bitmap中
                imageView.buildDrawingCache();
              //  HandPick_All_Static_Obj.bitmap=null;
                HandPick_All_Static_Obj.bitmap1 = imageView.getDrawingCache();
                ActivityTransitionLauncher.with(getActivity())
                        .from(imageView)
                        .launch(intent);
            }
        });
        return view;
    }

    //加载数据
    public void loadData(String path)
    {
        okHttpClient=new OkHttpClient();
        request=new Request.Builder().url(path).build();
        call=okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str=response.body().string();
                Log.i("AAA", "===360全景===="+str);
                Gson gson=new Gson();
                qj_bean=gson.fromJson(str, Find_QJ_Bean.class);
               listBeen.addAll(qj_bean.getItemList());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }
}
