package adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by admin on 2016/7/27.
 */
public class FindHotPopular_VP_Adapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;
    private List<String> tabList;
    public FindHotPopular_VP_Adapter(FragmentManager fm,List<Fragment> fragmentList,List<String> tabList) {
        super(fm);
        this.fragmentList=fragmentList;
        this.tabList=tabList;
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
    public CharSequence getPageTitle(int position) {
        return tabList.get(position);
    }
}
