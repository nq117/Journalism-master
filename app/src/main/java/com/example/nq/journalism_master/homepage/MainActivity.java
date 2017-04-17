package com.example.nq.journalism_master.homepage;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.nq.journalism_master.R;
import com.example.nq.journalism_master.SplashView;
import com.example.nq.journalism_master.adapter.MainPagerAdapter;
import com.example.nq.journalism_master.collection.CollectActivity;
import com.example.nq.journalism_master.customtabs.CustomFallback;
import com.example.nq.journalism_master.customtabs.CustomTabActivityHelper;
import com.example.nq.journalism_master.database.News;
import com.example.nq.journalism_master.homepage.about.AboutPreferenceActivity;
import com.example.nq.journalism_master.utils.CleariDialog;
import com.example.nq.journalism_master.utils.NotiDialog;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/3/25 晚.
 * complete 2017/4/17 15:10
 */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

//    private MainFragment mainFragment;
    public static final String FILTER = "com.ltnq.e_read.broadCast.adjustNewsFab";
    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private long exitTime = 0;  //计数退出
    private CustomTabsIntent.Builder customTabsIntent;

    private MainPagerAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
//    private FloatingActionButton fb;
    /**
     *top(头条，默认),shehui(社会),guonei(国内),guoji(国际),yule(娱乐)
     * ,tiyu(体育)junshi(军事),keji(科技),caijing(财经),shishang(时尚)
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
//        fb = (FloatingActionButton) findViewById(R.id.fab);
//        fb.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "dddd", Toast.LENGTH_LONG).show();
//            }
//        });
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null){
//            actionBar.setDisplayHomeAsUpEnabled(true);
//            actionBar.setHomeAsUpIndicator(R.drawable.ic_list_black_24dp);
//        }
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        toggle.syncState();
//        navView.setCheckedItem(this);
        navView.setNavigationItemSelectedListener(this);
        customTabsIntent = new CustomTabsIntent.Builder();
        customTabsIntent.setToolbarColor(MainActivity.this.getResources().getColor(R.color.colorPrimary));
        iniSplash();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.toolbar, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        mDrawerLayout.openDrawer(GravityCompat.START);
        switch (item.getItemId()){
//            case R.id.setting:
//                DataSupport.deleteAll(News.class);
//                Toast.makeText(MainActivity.this, "清除成功", Toast.LENGTH_LONG).show();
//                break;
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }

    //启动画面
    private void iniSplash() {
        SplashView.showSplashView(this, 3, R.drawable.shape, new SplashView.OnSplashViewActionListener() {
            @Override
            public void onSplashImageClick(String actionUrl) {
                Log.d("SplashView", "img clicked. actionUrl: " + actionUrl);
//                Toast.makeText(MainActivity.this, "img clicked.", Toast.LENGTH_SHORT).show();
                CustomTabActivityHelper.openCustomTab(
                        MainActivity.this,
                        customTabsIntent.build(),
                        Uri.parse(MainActivity.this.getString(R.string.nq_college)),
                        new CustomFallback() {
                            @Override
                            public void openUri(Activity activity, Uri uri) {
                                super.openUri(activity, uri);
                            }
                        }
                );
            }

            @Override
            public void onSplashViewDismiss(boolean initiativeDismiss) {
                Log.d("SplashView", "dismissed, initiativeDismiss: " + initiativeDismiss);
            }
        });

        // call this method anywhere to update splash view data
//        http://ww2.sinaimg.cn/large/72f96cbagw1f5mxjtl6htj20g00sg0vn.jpg
        SplashView.updateSplashData(this, "http://ww2.sinaimg.cn/large/72f96cbagw1f5mxjtl6htj20g00sg0vn.jpg", "http://jkyeo.com");
    }

    //navView 点击事件
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        mDrawerLayout.closeDrawer(GravityCompat.START);
        switch (item.getItemId()){
            case R.id.nav_home:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_bookmarks:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                Intent intent = new Intent(MainActivity.this, CollectActivity.class);
                startActivity(intent);
                break;
            case R.id.about:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                startActivity(new Intent(this,AboutPreferenceActivity.class));
                break;
            case R.id.night_theme:
                mDrawerLayout.closeDrawer(GravityCompat.START);
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
                        Toast.makeText(MainActivity.this, "清除成功", Toast.LENGTH_LONG).show();
                    }
                });
                break;
            case R.id.suggest:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                final NotiDialog dialog = new NotiDialog(this, "问题反馈", "QQ:\n1172921726");
                dialog.show();
                dialog.setNegativeListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                dialog.setPositiveListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                                        Toast.makeText(SettingActivity.this, "前往应用商城更新", Toast.LENGTH_SHORT).show();
                        CustomTabActivityHelper.openCustomTab(
                                MainActivity.this,
                                customTabsIntent.build(),
                                Uri.parse(MainActivity.this.getString(R.string.nq_college)),
                                new CustomFallback() {
                                    @Override
                                    public void openUri(Activity activity, Uri uri) {
                                        super.openUri(activity, uri);
                                    }
                                }
                        );
                    }
                });
                break;
        }

        return true;
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        adapter = new MainPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
//        MODE_FIXED  MODE_SCROLLABLE  可以滑动  GRAVITY_CENTER  GRAVITY_FILL 可以滑动
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

//    /**
//     * 按2下退出程序
//     * @param event
//     * @return
//     */
//    public boolean dispatchKeyEvent(KeyEvent event) {
//        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
//                && event.getAction() == KeyEvent.ACTION_DOWN
//                && event.getRepeatCount() == 0) {
//            if((System.currentTimeMillis()-exitTime) > 2000){
//                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
//                exitTime = System.currentTimeMillis();
//            } else {
//                finish();
//                System.exit(0);
//            }
//            return true;
//        }
//        return super.dispatchKeyEvent(event);
//    }

    //返回不退出程序
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(false);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
