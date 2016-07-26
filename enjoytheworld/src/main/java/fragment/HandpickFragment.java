package fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.three.enjoytheworld.R;

import java.io.IOException;
import java.util.List;

import adapter.HandPick_Adapter;
import bean.HandPick_Bean;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import recyclerview.recyclerview.XRecylcerView;
import uri.HandPick_Urls;

/**
 * A simple {@link Fragment} subclass.
 */
public class HandpickFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, XRecylcerView.LoadingListener {
    private SwipeRefreshLayout swipeRefreshLayout;
    private XRecylcerView xRecylcerView;

    private HandPick_Adapter mHandPick_adapter;

    private String addMaoreUrl = null;

    public HandpickFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_handpick, container, false);
        //初始化组件
        initView(view);
        //第一次加载数据
        getData_1();
        return view;
    }

    //初始化数据
    private void initView(View view) {
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe);
        xRecylcerView = (XRecylcerView) view.findViewById(R.id.xrecycleview);

        swipeRefreshLayout.setOnRefreshListener(this);
        xRecylcerView.setLoadingListener(this);

        xRecylcerView.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        xRecylcerView.setLayoutManager(manager);

    }

    //数据第一次加载和刷新
    private void getData_1() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(HandPick_Urls.first_page_url).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonData = response.body().string();
                Gson gson = new Gson();
                HandPick_Bean handPick_bean = gson.fromJson(jsonData, HandPick_Bean.class);
                final List<HandPick_Bean.IssueListBean.ItemListBean> itemList = handPick_bean.getIssueList().get(0).getItemList();
                itemList.addAll(handPick_bean.getIssueList().get(1).getItemList());
                final String nextPageUrl = handPick_bean.getNextPageUrl();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mHandPick_adapter = new HandPick_Adapter(getContext());
                        mHandPick_adapter.clear();
                        mHandPick_adapter.setList(itemList);
                        xRecylcerView.setAdapter(mHandPick_adapter);
                        addMaoreUrl = nextPageUrl;

                    }
                });
            }
        });
    }

    //加载请求
    private void getData_2() {

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(addMaoreUrl).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonData = response.body().string();
                Gson gson = new Gson();
                HandPick_Bean handPick_bean = gson.fromJson(jsonData, HandPick_Bean.class);
                final List<HandPick_Bean.IssueListBean.ItemListBean> itemList = handPick_bean.getIssueList().get(0).getItemList();
                itemList.addAll(handPick_bean.getIssueList().get(1).getItemList());
                final String nextPageUrl = handPick_bean.getNextPageUrl();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        mHandPick_adapter.addAll(itemList);
                        xRecylcerView.loadMoreComplete();
                        addMaoreUrl = nextPageUrl;
                    }
                });
            }
        });

    }


    @Override
    public void onLoadMore() {
        Toast.makeText(getContext(), "加载中", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getData_2();
            }
        }, 2000);

    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 判断当前是否处于上拉加载状态
                if (!xRecylcerView.isLoadData()) {
                    // 表示刷新完成
                    swipeRefreshLayout.setRefreshing(false);
                    // 设置当前刷新数，当下拉刷新的时候置为0，上拉加载时已经初始化
                    xRecylcerView.setPreviousTotal(0);
                    // 设置是否还有更多
                    xRecylcerView.setIsnomore(false);
                    getData_1();

                } else {
                    Toast.makeText(getContext(), "当前正在刷新中", Toast.LENGTH_SHORT).show();
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        }, 2000);
    }
}
