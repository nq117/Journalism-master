package com.example.nq.journalism_master.collection.Fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.example.nq.journalism_master.R;
import com.example.nq.journalism_master.adapter.NewAdapter;
import com.example.nq.journalism_master.bean.Data;
import com.example.nq.journalism_master.bean.JuheBean;
import com.example.nq.journalism_master.utils.ApiUtils;
import com.example.nq.journalism_master.utils.Constant;
import com.youth.banner.Banner;

import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/30.
 */

public class DoubanFragment extends Fragment {

    private List<Data> nmList;
//    private MyAdapter adapter;
    private NewAdapter adapter;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout swipeRefreshView;
    private Banner banner;

    String[] images=new String[]{
            "http://img.zcool.cn/community/0166c756e1427432f875520f7cc838.jpg",
            "http://img.zcool.cn/community/018fdb56e1428632f875520f7b67cb.jpg",
            "http://img.zcool.cn/community/01c8dc56e1428e6ac72531cbaa5f2c.jpg",
            "http://img.zcool.cn/community/01fda356640b706ac725b2c8b99b08.jpg",
            "http://img.zcool.cn/community/01fd2756e142716ac72531cbf8bbbf.jpg",
            "http://img.zcool.cn/community/0114a856640b6d32f87545731c076a.jpg"};
    String[] titles=new String[]{"全场2折起","十大星级品牌联盟","嗨购5折不要停",
            "12趁现在","嗨购5折不要停，12.12趁现在","实打实大顶顶顶顶"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);
        initData();
//        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
//        swipeRefreshView = (SwipeRefreshLayout) view.findViewById(R.id.refreshLayout);
        mRecyclerView.setHasFixedSize(true);
        nmList = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
//        adapter = new MyAdapter(getActivity(), nmList);
        adapter = new NewAdapter(getActivity(), nmList);
//        adapter = new MyAdapter(getActivity(), list);
        mRecyclerView.addItemDecoration(new ItemDecoration()); //添加装饰类
        mRecyclerView.setAdapter(adapter);
//        setHeaderView(mRecyclerView);
        setHeaderView(mRecyclerView);
        initSwipe();
        return view;
    }

    private void initData() {
        ApiUtils.Getjuhe(new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                loadGetLastest(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        }, Constant.Common.AppKey);
    }

    private void loadGetLastest(String result) {
        JuheBean juhe = JSON.parseObject(result.toString(), JuheBean.class);
        if (juhe != null) {
            nmList.addAll(juhe.result.data);
            Log.e("AAA", String.valueOf(nmList.size()));
            adapter.setTopAdapter(nmList);
            adapter.notifyDataSetChanged();
        }
    }

    private void initSwipe() {
        // 设置下拉进度的主题颜色
        swipeRefreshView.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
        // 下拉时触发SwipeRefreshLayout的下拉动画，动画完毕之后就会回调这个方法
        swipeRefreshView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                // 开始刷新，设置当前为刷新状态
                //swipeRefreshLayout.setRefreshing(true);

                // 这里是主线程
                // 一些比较耗时的操作，比如联网获取数据，需要放到子线程去执行
                // TODO 获取数据
                final Random random = new Random();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        initData();
//                        mRecyclerView.setHasFixedSize(true);
//                        list = new ArrayList<>();
//                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
//                        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//                        mRecyclerView.setLayoutManager(linearLayoutManager);
//                        adapter = new TopAdapter(getActivity(), list);
//                        mRecyclerView.addItemDecoration(new ItemDecoration()); //添加装饰类
                        mRecyclerView.setAdapter(adapter);
                        Snackbar.make(swipeRefreshView, "加载成功", Snackbar.LENGTH_SHORT).show();
                        // 加载完数据设置为不刷新状态，将下拉进度收起来
                        swipeRefreshView.setRefreshing(false);
                    }
                }, 1200);

                // System.out.println(Thread.currentThread().getName());

                // 这个不能写在外边，不然会直接收起来
                //swipeRefreshLayout.setRefreshing(false);
            }
        });
    }


    private void setHeaderView(RecyclerView view){
//        banner = (Banner) LayoutInflater.from(getActivity()).inflate(R.layout.list_header, view, false);
        View header = LayoutInflater.from(getActivity()).inflate(R.layout.list_header, view, false);
        banner = (Banner) header.findViewById(R.id.banner);
        banner.setBannerStyle(Banner.CIRCLE_INDICATOR_TITLE);
        banner.setIndicatorGravity(Banner.LEFT);
        banner.setBannerTitle(titles);
        banner.isAutoPlay(true);
        banner.setDelayTime(3000);
        banner.setImages(images, new Banner.OnLoadImageListener() {
            @Override
            public void OnLoadImage(ImageView view, Object url) {
                System.out.println("加载中");
                Glide.with(getActivity()).load(url).into(view);
                System.out.println("加载完");
            }
        });
        adapter.setHeaderView(header);
    }

    //添加线条
    class ItemDecoration extends RecyclerView.ItemDecoration {
        /**
         * @param outRect 边界
         * @param view    recyclerView ItemView
         * @param parent  recyclerView
         * @param state   recycler 内部数据管理
         */
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.set(0, 0, 0, 1);
        }

    }
}