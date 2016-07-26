package fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.three.enjoytheworld.R;

import recyclerview.recyclerview.XRecylcerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FindFragment extends Fragment {
    private SwipeRefreshLayout swipeRefreshLayout;
    private XRecylcerView xRecylcerView;


    public FindFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_find, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        swipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.swipe);
        xRecylcerView= (XRecylcerView) view.findViewById(R.id.xrecycleview);
    }


}
