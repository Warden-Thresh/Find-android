package com.warden.find.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;


import com.warden.find.Fragment.NewsDetailFragment;

import java.util.List;

/**
 * Created by WangChenchen on 2016/8/18.
 * 新闻页ViewPager适配器
 */
public class NewsAdapter extends FragmentPagerAdapter {

    private List<String> tabs;

    public NewsAdapter(FragmentManager fm, List<String> tabs) {
        super(fm);
        this.tabs = tabs;
    }

    public void setTabs(List<String> tabs) {
        this.tabs = tabs;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public Object instantiateItem(ViewGroup container, int position) {
        NewsDetailFragment f = (NewsDetailFragment) super.instantiateItem(container, position);
        if (tabs != null && position >= 0 && position < tabs.size()) {
            String mHint = tabs.get(position);
            f.resetFragmentData(mHint);
        }

        return f;
    }

    @Override
    public Fragment getItem(int position) {
        return NewsDetailFragment.newInstance(tabs.get(position));
    }

    @Override
    public int getCount() {
        return tabs.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs.get(position);
    }
}
