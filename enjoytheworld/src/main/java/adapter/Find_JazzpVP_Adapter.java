package adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import JazzyViewPager.JazzyViewPager;

/**
 * Created by admin on 2016/7/29.
 */
public class Find_JazzpVP_Adapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;
    private JazzyViewPager  mJazzy;
    public Find_JazzpVP_Adapter(FragmentManager fm,List<Fragment> fragmentList,JazzyViewPager  mJazzy) {
        super(fm);
        if(fragmentList==null)
        {
            this.fragmentList=new ArrayList<>();
        }else {
            this.fragmentList=fragmentList;
        }
        this.mJazzy=mJazzy;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object obj = super.instantiateItem(container, position);
        mJazzy.setObjectForPosition(obj, position);
        return obj;
    }
}
