package fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.three.enjoytheworld.AuthorItemActivity;
import com.three.enjoytheworld.R;

import java.io.IOException;
import java.util.List;

import adapter.AuthorAdapter;
import bean.AuthorBean;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import recyclerview.recyclerview.XRecylcerView;
import uri.AuthorUri;

/**
 * A simple {@link Fragment} subclass.
 */
public class AuthorFragment extends Fragment implements AuthorAdapter.OnItemClickListen {


    private static final String TAG = AuthorFragment.class.getSimpleName();
    private View view;
    private XRecylcerView mXRecylcerView;


    private AuthorBean mAuthorBean;
    private String mNextPageUrl;
    private List<AuthorBean.ItemListBean> data;
    private AuthorAdapter mAuthorAdapter;


    public AuthorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_author, container, false);

        initView();

        initData();
        return view;
    }

    private void initView() {

        mXRecylcerView = (XRecylcerView) view.findViewById(R.id.recyclerView_author);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mXRecylcerView.setLayoutManager(manager);


        mAuthorAdapter = new AuthorAdapter(getContext(), null);
        mAuthorAdapter.setOnItemClickListen(this);


    }

    private void loading(){
        if (!TextUtils.isEmpty(mNextPageUrl)){
            mXRecylcerView.setLoadingListener(new XRecylcerView.LoadingListener() {
                @Override
                public void onLoadMore() {
                    initData2();
                }
            });
        }else {
            mXRecylcerView.setIsnomore(true);
            //下一页地址为空
            View view1 = LayoutInflater.from(getContext()).inflate(R.layout.author_item0, null);
            TextView textView = (TextView) view1.findViewById(R.id.tv_author_text);
            textView.setText("The end");

            mXRecylcerView.addFootView(view1);
        }
    }


    String json;
    private void initData() {
        OkHttpClient okHttpClient = new OkHttpClient();

        String path = AuthorUri.HEAD + AuthorUri.FIRST + AuthorUri.TAIL;
        Request request = new Request.Builder().url(path).build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {


            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                json = response.body().string();

                Log.i(TAG, "onResponse: 解析之后的数据:" + json);

                mAuthorBean = new Gson().fromJson(json, AuthorBean.class);

//                Log.i(TAG, "onResponse:------ "+mAuthorBean.getItemList().get(7).getData().getItemList2().get(0).toString());

                data = mAuthorBean.getItemList();


                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.i(TAG, "onResponse: 解析之后的数据:" + json);
                        Log.i(TAG, "onResponse: "+mAuthorBean.toString());

                        mAuthorAdapter.setData(data);
                        mXRecylcerView.setAdapter(mAuthorAdapter);
                        //下一页数据
                        mNextPageUrl = mAuthorBean.getNextPageUrl();
                        loading();

                    }
                });
            }
        });
    }

    private void initData2() {
        if (!TextUtils.isEmpty(mNextPageUrl)){

            OkHttpClient okHttpClient = new OkHttpClient();

            Request request = new Request.Builder().url(mNextPageUrl).build();

            Call call = okHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {

                    String json = response.body().string();

                    Log.i(TAG, "onResponse: 解析之后的数据:" + json);

                    mAuthorBean = new Gson().fromJson(json, AuthorBean.class);


                    data = mAuthorBean.getItemList();


                    ((Activity)getContext()).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            mAuthorAdapter.setData(data);
                            mXRecylcerView.loadMoreComplete();
                            //下一页数据
                            mNextPageUrl = mAuthorBean.getNextPageUrl();
                            loading();
                        }
                    });
                }
            });
        }

    }

    @Override
    public void onItemClick(int position) {

        AuthorBean.ItemListBean bean = mAuthorAdapter.getData().get(position);
        String type = bean.getType();
        if ("briefCard".equals(type) || "videoCollectionWithBrief".equals(type)){
            Intent intent = new Intent(getContext(), AuthorItemActivity.class);

            AuthorBean.ItemListBean.DataBean beanData = bean.getData();
            if (beanData.getId() !=0 ){
                intent.putExtra("itemId", beanData.getId());
                intent.putExtra("title",beanData.getTitle());
            }else {
                intent.putExtra("itemId", beanData.getHeader().getId());
                intent.putExtra("title",bean.getData().getHeader().getTitle());
            }



            startActivity(intent);
        }


    }
}
