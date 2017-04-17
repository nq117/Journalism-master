package com.example.nq.journalism_master.homepage.webview;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.nq.journalism_master.R;

import java.util.Random;

import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by nq on 2017/3/28.
 */

public class WebActivity extends SwipeBackActivity {

    private static final String TAG = "WebActivity";

    private ImageView imageView;
    private WebView webView;
    private NestedScrollView scrollView;
    private Toolbar toolbar;
    private CollapsingToolbarLayout toolbarLayout;
    private SwipeRefreshLayout refreshLayout;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_layout);
        intent = getIntent();
        webView = (WebView) findViewById(R.id.web_view);
//        webView.loadUrl(intent.getStringExtra("URL"));
        imageView = (ImageView) findViewById(R.id.image_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        scrollView = (NestedScrollView) findViewById(R.id.scrollView);
        toolbarLayout.setTitle(intent.getStringExtra("NAME"));
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.refreshLayout);
        Glide.with(this)
                .load(intent.getStringExtra("IMG"))
                .asBitmap()
                .placeholder(R.drawable.placeholder)
                .centerCrop()
                .error(R.drawable.placeholder)
                .into(imageView);
        toolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        toolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        toolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBarPlus1);
        toolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBarPlus1);


//        toolbar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                scrollView.smoothScrollTo(0, 0);
//            }
//        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        initSwipe();
        initData();

    }

    private void initData() {
        webView.setScrollbarFadingEnabled(true);
        //能够和js交互
        webView.getSettings().setJavaScriptEnabled(true);
        //缩放,设置为不能缩放可以防止页面上出现放大和缩小的图标
        webView.getSettings().setBuiltInZoomControls(false);
        //缓存
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        //开启DOM storage API功能
        webView.getSettings().setDomStorageEnabled(true);
        //开启application Cache功能
        webView.getSettings().setAppCacheEnabled(false);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);//支持通过JS打开新窗口
        webView.setWebViewClient(new NoAdWebViewClient(this, intent.getStringExtra("URL")));

        webView.loadUrl(intent.getStringExtra("URL"));
//        webView.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return true;
//            }
//
//        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                this.onBackPressed();
                break;
        }
        return true;
    }

    private void initSwipe() {
        // 设置下拉进度的主题颜色
        refreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
        // 下拉时触发SwipeRefreshLayout的下拉动画，动画完毕之后就会回调这个方法
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
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
                        // 加载完数据设置为不刷新状态，将下拉进度收起来
                        refreshLayout.setRefreshing(false);
                    }
                }, 1200);

                // System.out.println(Thread.currentThread().getName());

                // 这个不能写在外边，不然会直接收起来
                //swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

}
