package com.example.nq.journalism_master.collection.Fragment;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.nq.journalism_master.BaseActivity;
import com.example.nq.journalism_master.R;
import com.example.nq.journalism_master.adapter.ImagNewsAdapter;
import com.example.nq.journalism_master.bean.Top.ImageNewsModel;
import com.example.nq.journalism_master.bean.Top.Photo;
import com.example.nq.journalism_master.utils.Constant;
import com.example.nq.journalism_master.utils.OkHttpUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/4/11.
 */
public class ImageNewsActivity extends BaseActivity {

    private static final String TAG = "ImageNewsActivity";
    private static final int ON_SUCCESS = 0;
    private static final int ON_FAILURE = 1;
    private String url;
    private String title;
    private List<Photo> list = new ArrayList<>();
    private ImagNewsAdapter adapter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.pb_image_news)
    ProgressBar pb_image_news;
    @BindView(R.id.vp_image_news)
    ViewPager vp_image_news;
    @BindView(R.id.tv_title_image_news)
    TextView tv_title_image_news;
    @BindView(R.id.tv_page_image_news)
    TextView tv_page_image_news;
    @BindView(R.id.tv_image_news)
    TextView tv_image_news;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case ON_SUCCESS:
                    pb_image_news.setVisibility(View.GONE);
                    vp_image_news.setVisibility(View.VISIBLE);
                    tv_title_image_news.setVisibility(View.VISIBLE);
                    tv_page_image_news.setVisibility(View.VISIBLE);
                    tv_image_news.setVisibility(View.VISIBLE);

                    tv_image_news.setText(list.get(0).getNote());
                    tv_page_image_news.setText(1 + "/" + list.size());
                    tv_title_image_news.setText(title);

                    adapter = new ImagNewsAdapter(ImageNewsActivity.this, list);
                    vp_image_news.setAdapter(adapter);
                    vp_image_news.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                        @Override
                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                        }

                        @Override
                        public void onPageSelected(int position) {
                            tv_page_image_news.setText((position + 1) + "/" + list.size());
                            tv_image_news.setText(list.get(position).getNote());
                            Log.e(TAG, list.get(position).getNote());
                        }

                        @Override
                        public void onPageScrollStateChanged(int state) {

                        }
                    });
                    break;
                case ON_FAILURE:
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_news);
        ButterKnife.bind(this);

        initView();

        title = getIntent().getStringExtra("title");
        // 获取url
        url = getIntent().getStringExtra("url");
        Log.e(TAG+"0", url);
        // 对url进行重新拼接
        url = url.substring(4);
        url = Constant.Url.PHOTO_SET + url.substring(0, 4) + "/" + url.substring(5) + ".json";
        Log.e(TAG+"1", url);
        initData();
    }

    private void initView() {

        // 当版本大于5.0，调用方法更改状态栏颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.BLACK);   //这里动态修改颜色
        }

        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(Color.BLACK);
        // 这句话保证title能被修改
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // 给HomeButton设置点击事件
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initData() {
        new Thread() {
            @Override
            public void run() {
                String str = OkHttpUtils.getResponse(url);
                Gson gson = new Gson();
                ImageNewsModel inm = gson.fromJson(str, ImageNewsModel.class);
                Log.e(TAG, String.valueOf(inm.photos));
                list = inm.photos;
                if (list.size() > 0) {
                    handler.sendEmptyMessage(ON_SUCCESS);
                } else {
                    handler.sendEmptyMessage(ON_FAILURE);
                }
            }
        }.start();

    }
}
