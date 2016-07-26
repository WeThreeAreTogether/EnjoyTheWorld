package fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.three.enjoytheworld.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AuthorFragment extends Fragment {


    private View view;
    private ExpandableListView lv;

    public AuthorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_author, container, false);

        lv = ((ExpandableListView) view.findViewById(R.id.expandableListView));

        return view;
    }

}
