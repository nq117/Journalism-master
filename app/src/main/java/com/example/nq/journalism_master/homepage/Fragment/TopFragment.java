package com.example.nq.journalism_master.homepage.Fragment;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.example.nq.journalism_master.R;
import com.example.nq.journalism_master.adapter.TopAdapter;
import com.example.nq.journalism_master.adapter.TopAdapterClick;
import com.example.nq.journalism_master.utils.ApiUtils;
import com.example.nq.journalism_master.bean.Data;
import com.example.nq.journalism_master.bean.JuheBean;
import com.example.nq.journalism_master.homepage.webview.WebActivity;
import com.example.nq.journalism_master.utils.Constant;

import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2017/3/28.
 */

public class TopFragment extends Fragment {

    private List<Data> list;
    private RecyclerView mRecyclerView;
    private TopAdapter adapter;
//    private MyAdapter adapter;
    private SwipeRefreshLayout swipeRefreshView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        initData();

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        swipeRefreshView = (SwipeRefreshLayout) view.findViewById(R.id.refreshLayout);
        mRecyclerView.setHasFixedSize(true);
        list = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        adapter = new TopAdapter(getActivity(), list);
//        adapter = new MyAdapter(getActivity(), list);
        mRecyclerView.addItemDecoration(new ItemDecoration()); //添加装饰类
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnItemTouchListener(new TopAdapterClick(getActivity(), mRecyclerView,
        new TopAdapterClick.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Data data = list.get(position);
                Intent intent = new Intent(getActivity(), WebActivity.class);
                String url = data.getUrl();
                String img = data.getThumbnail_pic_s();
                String title = data.getAuthor_name();
                intent.putExtra("URL", url);
                intent.putExtra("IMG", img);
                intent.putExtra("NAME", title);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));
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
        if (juhe != null){
            list.addAll(juhe.result.data);
            adapter.setTopAdapter(list);
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
                        mRecyclerView.setHasFixedSize(true);
                        list = new ArrayList<>();
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        mRecyclerView.setLayoutManager(linearLayoutManager);
                        adapter = new TopAdapter(getActivity(), list);
                        mRecyclerView.addItemDecoration(new ItemDecoration()); //添加装饰类
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
    //添加线条
    class ItemDecoration extends RecyclerView.ItemDecoration{
        /**
         *
         * @param outRect 边界
         * @param view recyclerView ItemView
         * @param parent recyclerView
         * @param state recycler 内部数据管理
         */
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.set(0,0,0,1);
        }
    }
}
