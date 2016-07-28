package fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.three.enjoytheworld.R;

import java.util.List;

import adapter.AuthorItemAdapter;
import bean.AuthorItemBean;
import recyclerview.recyclerview.XRecylcerView;
import uri.AuthorUri;
import utils.AuthorLoadData;

/**
 * A simple {@link Fragment} subclass.
 */
public class AuthorItemFragment extends Fragment {


    private static final String TAG = AuthorItemFragment.class.getSimpleName();
    private View view;
    private XRecylcerView xRecyclerView;

    private int itemId;

    private String path;
    private int index;

    private AuthorLoadData mAuthorLoadData;
    private AuthorItemAdapter mAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_author_item, container, false);

        Bundle bundle = getArguments();
        itemId = bundle.getInt("itemId");
        index = bundle.getInt("index");
        initView();


        return view;
    }

    private void initView() {

        xRecyclerView = ((XRecylcerView) view.findViewById(R.id.xRecyclerView_author));

        if (index == 0){
            path = AuthorUri.HEAD + String.format(AuthorUri.ITEM,itemId,"date")+AuthorUri.TAIL;

        }else {
            path = AuthorUri.HEAD + String.format(AuthorUri.ITEM,itemId,"shareCount")+AuthorUri.TAIL;

        }
        mAuthorLoadData = new AuthorLoadData(getContext(),path);
        mAuthorLoadData.loadData();
        Log.i(TAG, "initView: "+path);
        mAuthorLoadData.setListener(new AuthorLoadData.OnDataLoadedListener() {
            @Override
            public void onDataLoadedListener(String json) {

                Log.i(TAG, "onDataLoadedListener: json:"+json);
                AuthorItemBean authorItemBean = new Gson().fromJson(json, AuthorItemBean.class);
                Log.i(TAG, "onDataLoadedListener: 解析成功："+authorItemBean);
                List<AuthorItemBean.ItemListBean> data = authorItemBean.getItemList();

                LinearLayoutManager manager = new LinearLayoutManager(getContext());
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                xRecyclerView.setLayoutManager(manager);

                mAdapter = new AuthorItemAdapter(getContext(),null);
                mAdapter.setData(data);
                xRecyclerView.setAdapter(mAdapter);
                Log.i(TAG, "onDataLoadedListener: 适配成功");

            }

        });
    }

}
