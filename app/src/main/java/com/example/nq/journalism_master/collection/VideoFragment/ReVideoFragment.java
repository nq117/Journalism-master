package com.example.nq.journalism_master.collection.VideoFragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

import com.alibaba.fastjson.JSON;
import com.example.nq.journalism_master.R;
import com.example.nq.journalism_master.adapter.HotVideoAdapter;
import com.example.nq.journalism_master.bean.Video.VideoBean;
import com.example.nq.journalism_master.bean.Video.VideoModel;
import com.example.nq.journalism_master.utils.LiteUtils;
import com.example.nq.journalism_master.database.Video;
import com.example.nq.journalism_master.utils.ApiUtils;
import com.example.nq.journalism_master.utils.NetworkState;
import com.example.nq.journalism_master.utils.PreferencesUtil;

import org.litepal.crud.DataSupport;
import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/30.
 */

public class ReVideoFragment extends Fragment{

    private final static int REVIDEO = 1;
    private final static int SDF = 0X02;
    private HotVideoAdapter adapter;
    private List<VideoModel> mList;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout swipeRefreshView;
    private PreferencesUtil preferencesUtil;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case REVIDEO:
                    VideoBean hot = JSON.parseObject(preferencesUtil.getString("ReVideo", null), VideoBean.class);
                    mList.addAll(hot.V9LG4CHOR);
                    adapter.notifyDataSetChanged();
                    break;
                case SDF:
                    initData();
                    mRecyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);
        preferencesUtil = new PreferencesUtil(getActivity());
        List<Video> video = DataSupport.findAll(Video.class);
        if (video != null && NetworkState.networkConnected(getActivity()) == false) {
            for (int i = 0; i<video.size(); i++) {
                switch (String.valueOf(video.get(i).getName())) {
                    case "ReVideoFragment":
                        mHandler.sendEmptyMessage(REVIDEO);
                        break;
                }
            }
        } else {
            initData();
        }
        mList = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        adapter = new HotVideoAdapter(getActivity(), mList);
        mRecyclerView.setAdapter(adapter);
        initSwipe();
        return view;
    }

    private void initData() {
        ApiUtils.GetRecVideo(new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                VideoBean hot = JSON.parseObject(result, VideoBean.class);
                Log.e("aa", String.valueOf(hot));
                if (hot != null) {
                    mList.addAll(hot.V9LG4CHOR);
                    adapter.setTopAdapter(mList);
                    adapter.notifyDataSetChanged();
                    LiteUtils.setLiteVideo("ReVideoFragment", getActivity());
                    String json = JSON.toJSONString(hot);
                    preferencesUtil.setString("ReVideo", json);
                }
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
        });
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
                        if (NetworkState.networkConnected(getActivity()) == true) {
                            mHandler.sendEmptyMessage(SDF);
                            Snackbar.make(swipeRefreshView, "加载成功", Snackbar.LENGTH_SHORT).show();
                        } else {
                            Snackbar.make(swipeRefreshView, "请检查网络", Snackbar.LENGTH_SHORT).show();
                        }
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
}
