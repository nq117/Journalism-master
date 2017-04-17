package com.example.nq.journalism_master.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.nq.journalism_master.homepage.Fragment.DomesticFragment;
import com.example.nq.journalism_master.homepage.Fragment.FashionFragment;
import com.example.nq.journalism_master.homepage.Fragment.FinanceFragment;
import com.example.nq.journalism_master.homepage.Fragment.InternationFragment;
import com.example.nq.journalism_master.homepage.Fragment.MilitaryFragment;
import com.example.nq.journalism_master.homepage.Fragment.RecreationFragment;
import com.example.nq.journalism_master.homepage.Fragment.SocialFragment;
import com.example.nq.journalism_master.homepage.Fragment.SportsFragment;
import com.example.nq.journalism_master.homepage.Fragment.StFragment;
import com.example.nq.journalism_master.homepage.Fragment.TopNewsFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/3/26.
 */
public class MainPagerAdapter extends FragmentPagerAdapter{
    //    ,"国际","娱乐","体育","军事","科技","财经","时尚"
    /**
     *top(头条，默认),shehui(社会),guonei(国内),guoji(国际),yule(娱乐)
     * ,tiyu(体育)junshi(军事),keji(科技),caijing(财经),shishang(时尚)
     *
     */
    private String[] title = {"头条","社会","国内","国际","娱乐","体育","军事","科技","财经","时尚"};
    private List<Fragment> fragmentlist = new ArrayList<>();

    public MainPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        fragmentlist.add(new TopNewsFragment());
        fragmentlist.add(new SocialFragment());
        fragmentlist.add(new DomesticFragment());
        fragmentlist.add(new InternationFragment());
        fragmentlist.add(new RecreationFragment());
        fragmentlist.add(new SportsFragment());
        fragmentlist.add(new MilitaryFragment());
        fragmentlist.add(new StFragment());
        fragmentlist.add(new FinanceFragment());
        fragmentlist.add(new FashionFragment());
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
