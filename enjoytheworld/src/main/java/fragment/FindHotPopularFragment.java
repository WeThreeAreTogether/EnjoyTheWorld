package fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import bean.FindPopular_LVBean;
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
public class FindHotPopularFragment extends Fragment {
    private ListView listView;
    private View view;
    private FindPopular_LV_Adapter adapter;
    private OkHttpClient okHttpClient;
    private Request request;
    private Call call;
    private List<FindPopular_LVBean.ItemListBean> listBeen=new ArrayList<>();
    private boolean flag=false;
    private FindPopular_LVBean bean;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.find_popular_listview,container,false);
        listView= (ListView) view.findViewById(R.id.listview);
        Bundle bundle=getArguments();
        switch (bundle.getInt("flag")) {
            case 0:
                //周排行
                loadData(String.format(FindUri.PopularBase,"weekly"));
                break;
            case 1:
                //月排行
                loadData(String.format(FindUri.PopularBase,"monthly"));
                break;
            case 2:
                //总排行
                loadData(String.format(FindUri.PopularBase,"historical"));
                break;

        }
        adapter=new FindPopular_LV_Adapter(listBeen,getContext());
        listView.setAdapter(adapter);
        //listView的点击监听事件
        setListener();
        return view;
    }

    private void setListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent=new Intent(getActivity(),FindFiveActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("data",listBeen.get(i).getData());
                intent.putExtras(bundle);
                intent.putExtra("flag",0);
                //获取点击的listview的item上的图片
                ImageView imageView= (ImageView) view.findViewById(R.id.iv);
                //获取ImageView上的图片,并保存到全局变量bitmap中
                imageView.buildDrawingCache();
                HandPick_All_Static_Obj.bitmap = imageView.getDrawingCache();
                ActivityTransitionLauncher.with(getActivity())
                                            .from(imageView)
                                            .launch(intent);
            }
        });
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
                Gson gson=new Gson();
               // listBeen=gson.fromJson(str,FindPopular_LVBean.class).getItemList();
                bean=gson.fromJson(str,FindPopular_LVBean.class);
                listBeen.addAll(bean.getItemList());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        adapter=new FindPopular_LV_Adapter(listBeen,getContext());
//                        listView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }
}
