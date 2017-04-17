package com.example.nq.journalism_master.collection.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
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
import com.example.nq.journalism_master.utils.ApiUtils;

import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/30.
 */

public class ZhihuDailyFragment extends Fragment{

    private HotVideoAdapter adapter;
    private List<VideoModel> mList;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout swipeRefreshView;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);
        initData();
        mList = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        adapter = new HotVideoAdapter(getActivity(), mList);
        mRecyclerView.setAdapter(adapter);
        return view;
    }

    private void initData() {
        ApiUtils.GetHotVideo(new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                VideoBean hot = JSON.parseObject(result, VideoBean.class);
                Log.e("aa", String.valueOf(hot));
                if (hot != null) {
                    mList.addAll(hot.V9LG4E6VR);
                    adapter.setTopAdapter(mList);
                    adapter.notifyDataSetChanged();
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
}
