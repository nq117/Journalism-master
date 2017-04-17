package com.example.nq.journalism_master.homepage.Fragment;

import android.content.Intent;
import android.graphics.Rect;
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
import com.example.nq.journalism_master.adapter.LoopAdapter;
import com.example.nq.journalism_master.adapter.VideoAdapter;
import com.example.nq.journalism_master.bean.Top.Ads;
import com.example.nq.journalism_master.bean.Top.NewsModel;
import com.example.nq.journalism_master.bean.Top.TopModel;
import com.example.nq.journalism_master.collection.Fragment.ImageNewsActivity;
import com.example.nq.journalism_master.utils.LiteUtils;
import com.example.nq.journalism_master.database.News;
import com.example.nq.journalism_master.homepage.webview.WebNewsActivity;
import com.example.nq.journalism_master.utils.ApiUtils;
import com.example.nq.journalism_master.utils.Constant;
import com.example.nq.journalism_master.utils.NetworkState;
import com.example.nq.journalism_master.utils.PreferencesUtil;
import com.jude.rollviewpager.OnItemClickListener;
import com.jude.rollviewpager.RollPagerView;

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

public class TopNewsFragment extends Fragment implements VideoAdapter.OnItemClickListener{

    private final static int SAD = 0X00;
    private final static int SDD = 0X01;
    private final static int SDF = 0X02;
    private List<NewsModel> nmList;
    private NewsModel model = null;
    private VideoAdapter adapter;
    private String newsdb;  //数据库
    private String newsJSON;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout swipeRefreshView;
    public RollPagerView rollPagerView;
    private PreferencesUtil preferencesUtil;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SAD:
                    // 实现轮播效果
                    rollPagerView.setAdapter(new LoopAdapter(rollPagerView, nmList.get(0).ads));
                    break;
                case SDD:
                    TopModel juhe = JSON.parseObject(preferencesUtil.getString("Top", null), TopModel.class);
                    nmList.addAll(juhe.T1348647909107);
                    rollPagerView.setAdapter(new LoopAdapter(rollPagerView, nmList.get(0).ads));
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
        List<News> newses = DataSupport.findAll(News.class);
//        for (News news: newses) {
//            newsdb = news.getName();
//        }
//        if (newsdb != null && NetworkState.networkConnected(getActivity()) == false) {
//            for (int i = 0; i<newses.size(); i++) {
//            Log.e("遍历", String.valueOf(newses.get(i).getName()));
//                switch (String.valueOf(newses.get(i).getName())) {
//                    case "TopNewsFragment":
//                    Log.e("遍历", "来过");
//                        handler.sendEmptyMessage(SDD);
//                        break;
//                }
//            }
//        } else {
//            initData();
//        }
        if (newses != null && NetworkState.networkConnected(getActivity()) == false) {
            for (int i = 0; i<newses.size(); i++) {
                Log.e("遍历", String.valueOf(newses.get(i).getName()));
                switch (String.valueOf(newses.get(i).getName())) {
                    case "TopNewsFragment":
                        Log.e("遍历", "来过");
                        if (NetworkState.networkConnected(getActivity()) == false) {
                            handler.sendEmptyMessage(SDD);
                        }
                        break;
                }
            }
        } else {
            initData();
        }
//        for (int i = 0; i<newses.size(); i++) {
//            Log.e("遍历", String.valueOf(newses.get(i).getName()));
//            switch (String.valueOf(newses.get(i).getName())) {
//                case "TopNewsFragment":
//                    Log.e("遍历", "来过");
//                    if (NetworkState.networkConnected(getActivity()) == false) {
//                        handler.sendEmptyMessage(SDD);
//                    }
//                    break;
//            }
//        }

//        model = JSON.parseObject(preferencesUtil.getString("Top",null), NewsModel.class);
//        Log.e("存储", model.getDocid());
        // 字符串等于类 且在没有网络的情况下读取缓存
//        if (newsdb != null && newsdb.equals("TopNewsFragment") && NetworkState.networkConnected(getActivity()) == false) {
//            News juhe = JSON.parseObject(newsJSON, News.class);
//            nmList.addAll(juhe.getData());
//            Log.e("数据库", String.valueOf(juhe.T1348647909107));
//            handler.sendEmptyMessage(SAD);
//            adapter.notifyDataSetChanged();
//            model = JSON.parseObject(preferencesUtil.getString("Top",null), NewsModel.class);
//            Log.e("存储", model.getDocid());
//            nmList.addAll(model);
//            NewsModel model = JSON.parseObject(preferencesUtil.getString("Top", null), NewsModel.class);
//            TopModel juhe = JSON.parseObject(preferencesUtil.getString("Top", null), TopModel.class);
//            nmList.addAll(juhe.T1348647909107);
//            handler.sendEmptyMessage(SDD);
//        } else {
//            initData();
//        }
//        TopModel juhe_1 = JSON.parseObject(preferencesUtil.getString("Top", null), TopModel.class);
//        Log.e("数据库_1", String.valueOf(juhe_1.T1348647909107));
        mRecyclerView.setHasFixedSize(true);
        nmList = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        adapter = new VideoAdapter(getActivity(), nmList);
        adapter.setOnItemClickListener(this);
        mRecyclerView.addItemDecoration(new ItemDecoration()); //添加装饰类
        mRecyclerView.setAdapter(adapter);
        setHeaderView(mRecyclerView);
        initSwipe();
        return view;
    }

    // 添加参数跳转到图片新闻详情
    private void toImageNewsActivity(String u, String t) {
        Intent intent = new Intent(getActivity(), ImageNewsActivity.class);
        intent.putExtra("url", u);
        intent.putExtra("title", t);
        startActivity(intent);
    }

    private void toWebNewsActivity(String docid) {
        Intent intent = new Intent(getActivity(), WebNewsActivity.class);
        intent.putExtra("url", Constant.Url.TOUCH_HEAD + docid + Constant.Url.TOUCH_END);
        startActivity(intent);
    }

    private void initData() {
        ApiUtils.GetTop(new Callback.CacheCallback<String>() {
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
        });
    }

    private void loadGetLastest(String result) {
        TopModel juhe = JSON.parseObject(result.toString(), TopModel.class);
        if (juhe != null) {
            nmList.addAll(juhe.T1348647909107);
            Log.e("数据库", String.valueOf(juhe.T1348647909107));
            handler.sendEmptyMessage(SAD);
            adapter.notifyDataSetChanged();
//            Connector.getDatabase();
//            News news = new News();
//            news.setName("TopNewsFragment");
//            news.save();
            LiteUtils.setLite("TopNewsFragment", getActivity());
            Log.e("bb", String.valueOf(juhe));
            String json = JSON.toJSONString(juhe);
            preferencesUtil.setString("Top", json);
//            Log.e("cc", preferencesUtil.getString("Top", null));
//            nmList.addAll(juhe.T1348647909107);
//            if (news.save()) {
//                Toast.makeText(getActivity(), "存储成功", Toast.LENGTH_SHORT).show();
//            } else {
//                handler.sendEmptyMessage(SDD);
//                Toast.makeText(getActivity(), "存储失败", Toast.LENGTH_SHORT).show();
//            }
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
                        if (NetworkState.networkConnected(getActivity()) == true) {
                            handler.sendEmptyMessage(SDF);
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


    private void setHeaderView(RecyclerView view){
        rollPagerView = (RollPagerView) LayoutInflater.from(getActivity()).inflate(R.layout.fragment_header, view, false);
        adapter.setHeaderView(rollPagerView);
        rollPagerView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                final NewsModel newsModel = nmList.get(0);
                List<Ads> ads = newsModel.ads;
                Ads ad = ads.get(position);
                if (ad.getUrl().contains("|")) {
                    Log.e("rollPagerView", ad.getUrl());
                    toImageNewsActivity(ad.getUrl(), ad.getTitle());
                } else {
                    toWebNewsActivity(ad.getUrl());
                }
            }
        });
    }

    @Override
    public void onItemClick(int position, Object object) {
        NewsModel nm = nmList.get(position);
        Log.e("Goukr", String.valueOf(nm.imgextra));
        if (nm.imgextra != null && !nm.imgextra.isEmpty()) {
            if (nm.photosetID != null && !nm.photosetID.equals(""))
                // 如果是图片新闻，则跳转到图片新闻详情
                toImageNewsActivity(nm.photosetID, nm.getTitle());
            else
                toWebNewsActivity(nm.docid);
        } else {
            // 如果是网页新闻，则用WebView加载网页
            toWebNewsActivity(nm.docid);
        }
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
