package com.example.nq.journalism_master.collection.Fragment;

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
import com.example.nq.journalism_master.homepage.webview.WebNewsActivity;
import com.example.nq.journalism_master.utils.ApiUtils;
import com.example.nq.journalism_master.utils.Constant;
import com.jude.rollviewpager.OnItemClickListener;
import com.jude.rollviewpager.RollPagerView;
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

public class GoukrFragment extends Fragment implements VideoAdapter.OnItemClickListener{

    private final static int SAD = 0X00;
    private List<NewsModel> nmList;
    //    private List<Ads> mList;
    private NewsModel model;
    //    private MyAdapter adapter;
    private VideoAdapter adapter;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout swipeRefreshView;
    public RollPagerView rollPagerView;

    private Banner banner;
    private String img ;
    private String title;
    String[] images=new String[]{
            "http://img.zcool.cn/community/0166c756e1427432f875520f7cc838.jpg",
            "http://img.zcool.cn/community/018fdb56e1428632f875520f7b67cb.jpg",
            "http://img.zcool.cn/community/01c8dc56e1428e6ac72531cbaa5f2c.jpg",
            "http://img.zcool.cn/community/01fda356640b706ac725b2c8b99b08.jpg",
            "http://img.zcool.cn/community/01fd2756e142716ac72531cbf8bbbf.jpg",
            "http://img.zcool.cn/community/0114a856640b6d32f87545731c076a.jpg"};
    String[] titles=new String[]{"全场2折起","十大星级品牌联盟","嗨购5折不要停",
            "12趁现在","嗨购5折不要停，12.12趁现在","实打实大顶顶顶顶"};

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SAD:
                    // 实现轮播效果
                    setHeaderView(mRecyclerView);
                    rollPagerView.setAdapter(new LoopAdapter(rollPagerView, nmList.get(0).ads));
                    break;
            }
        }
    };

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
//        mList = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
//        adapter = new MyAdapter(getActivity(), nmList);
        adapter = new VideoAdapter(getActivity(), nmList);
        adapter.setOnItemClickListener(this);
        mRecyclerView.addItemDecoration(new ItemDecoration()); //添加装饰类
        mRecyclerView.setAdapter(adapter);
//        mRecyclerView.addOnItemTouchListener(new TopAdapterClick(getActivity(), mRecyclerView,
//                new TopAdapterClick.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(View view, int position) {
//                        NewsModel nm = nmList.get(position);
//                        Log.e("Goukr", String.valueOf(nm.imgextra));
//                        if (nm.imgextra != null && nm.imgextra.isEmpty()) {
//                            if (nm.photosetID != null && !nm.photosetID.equals(""))
//                                // 如果是图片新闻，则跳转到图片新闻详情
//                                toImageNewsActivity(nm.photosetID, nm.getTitle());
//                            else
//                                toWebNewsActivity(nm.docid);
//                        } else {
//                            // 如果是网页新闻，则用WebView加载网页
//                            toWebNewsActivity(nm.docid);
//                        }
//                    }
//
//                    @Override
//                    public void onItemLongClick(View view, int position) {
//
//                    }
//                }));
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
//            Log.e("TAG1", String.valueOf(nmList.get(0).ads.get(0).getImgsrc()));
            nmList.addAll(juhe.T1348647909107);
            handler.sendEmptyMessage(SAD);
//            mList.addAll(juhe.T1348647909107.get(0).ads);
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
                        adapter.notifyDataSetChanged();
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
//        View header = LayoutInflater.from(getActivity()).inflate(R.layout.list_header, view, false);
//        banner = (Banner) header.findViewById(R.id.banner);
//        banner.setBannerStyle(Banner.CIRCLE_INDICATOR_TITLE);
//        banner.setIndicatorGravity(Banner.LEFT);
//        banner.setBannerTitle(titles);
//        banner.isAutoPlay(true);
//        banner.setDelayTime(3000);
//        banner.setImages(images, new Banner.OnLoadImageListener() {
//            @Override
//            public void OnLoadImage(ImageView view, Object url) {
//                System.out.println("加载中");
//                Glide.with(getActivity()).load(url).into(view);
//                System.out.println("加载完");
//            }
//        });
//        adapter.setHeaderView(header);
    }

    @Override
    public void onItemClick(int position, Object object) {
        NewsModel nm = nmList.get(position);
        Log.e("Goukr", String.valueOf(nm.imgextra));
        if (nm.imgextra != null && nm.imgextra.isEmpty()) {
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
