package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
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
    private List<FindPopular_LVBean.ItemListBean> listBeen;
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
                Gson gson=new Gson();
                listBeen=gson.fromJson(str,FindPopular_LVBean.class).getItemList();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter=new FindPopular_LV_Adapter(listBeen,getContext());
                        listView.setAdapter(adapter);
                    }
                });
            }
        });
    }
}
