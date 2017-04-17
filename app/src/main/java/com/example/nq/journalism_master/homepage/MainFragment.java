//package com.example.nq.journalism_master.homepage;
//
//import android.support.v4.app.Fragment;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.design.widget.TabLayout;
//import android.support.v4.view.ViewPager;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.example.nq.journalism_master.R;
//
///**
// * Created by Administrator on 2017/3/26.
// */
//
//public class MainFragment extends Fragment{
//
//    private MainPagerAdapter adapter;
//    private TabLayout tabLayout;
//    private ViewPager viewPager;
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_main, container, false);
//        initView();
//        return view;
//    }
//
//    private void initView() {
//        viewPager = (ViewPager) getView().findViewById(R.id.view_pager);
//        tabLayout = (TabLayout) getView().findViewById(R.id.tab_layout);
//        adapter = new MainPagerAdapter(getFragmentManager());
//        viewPager.setAdapter(adapter);
//        tabLayout.setupWithViewPager(viewPager);
//    }
//}
