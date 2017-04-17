package com.example.nq.journalism_master.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.nq.journalism_master.collection.VideoFragment.FunnyVideoFragment;
import com.example.nq.journalism_master.collection.VideoFragment.HotVideoFragment;
import com.example.nq.journalism_master.collection.VideoFragment.ReVideoFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/30.
 */

public class CollectPagerAdapter extends FragmentPagerAdapter{

    private String[] title = {"热点","娱乐","搞笑"};
    private List<Fragment> fragmentlist = new ArrayList<>();

    public CollectPagerAdapter(FragmentManager fm) {
        super(fm);
        fragmentlist.add(new HotVideoFragment());
        fragmentlist.add(new ReVideoFragment());
        fragmentlist.add(new FunnyVideoFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentlist.get(position);
    }

    @Override
    public int getCount() {
        return fragmentlist.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
