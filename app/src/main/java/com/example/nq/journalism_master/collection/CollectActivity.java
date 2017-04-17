package com.example.nq.journalism_master.collection;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.nq.journalism_master.BaseActivity;
import com.example.nq.journalism_master.R;
import com.example.nq.journalism_master.adapter.CollectPagerAdapter;
import com.example.nq.journalism_master.database.News;
import com.example.nq.journalism_master.database.Video;
import com.example.nq.journalism_master.homepage.MainActivity;
import com.example.nq.journalism_master.utils.CleariDialog;

import org.litepal.crud.DataSupport;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CollectActivity extends BaseActivity {

    @BindView(R.id.Coll_toolbar)
    Toolbar coll_toolbar;
    @BindView(R.id.Coll_tab_layout)
    TabLayout coll_tab;
    @BindView(R.id.Coll_view_pager)
    ViewPager coll_pager;
    private CollectPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        ButterKnife.bind(this);
        setSupportActionBar(coll_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        iniData();
    }

    private void iniData() {
        adapter = new CollectPagerAdapter(getSupportFragmentManager());
        coll_pager.setAdapter(adapter);
        coll_tab.setupWithViewPager(coll_pager);
//        MODE_FIXED  MODE_SCROLLABLE  可以滑动  GRAVITY_CENTER  GRAVITY_FILL 可以滑动
        coll_tab.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                this.onBackPressed();
                break;
            case R.id.setting:
                final CleariDialog dg = new CleariDialog(this, "清除缓存");
                dg.show();
                dg.setNegativeListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                dg.setPositiveListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DataSupport.deleteAll(News.class);
                        Toast.makeText(CollectActivity.this, "清除成功", Toast.LENGTH_LONG).show();
                    }
                });
                break;
        }
        return true;
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        ButterKnife.unbind(this);//解除绑定，官方文档只对fragment做了解绑
//    }
}
