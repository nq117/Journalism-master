package com.example.nq.journalism_master.app;

import android.app.Application;
import android.util.DisplayMetrics;

import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

import org.litepal.LitePal;
import org.xutils.x;

/**
 * Created by Administrator on 2017/3/27.
 */

public class App extends Application {

    /** 屏幕的宽度 */
    public int WIDTH;
    /** 屏幕的高度 */
    public int HEIGHT;

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        LitePal.initialize(this);
        /** 获取设备相关属性 **/
        DisplayMetrics dm = getResources().getDisplayMetrics();
        WIDTH = dm.widthPixels;
        HEIGHT = dm.heightPixels;
        initImageLoader();
    }
    /**
     * 初始化图片加载
     */
    private void initImageLoader() {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                this)
                .memoryCacheExtraOptions(WIDTH, HEIGHT) // max width, max
                .memoryCacheExtraOptions(480, 800) // max width, max height，即保存的每个缓存文件的最大长宽
                .threadPoolSize(3)// 线程池内加载的数量
                .threadPriority(Thread.NORM_PRIORITY - 2)// default 设置当前线程的优先级
                .memoryCache(new WeakMemoryCache())

                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 *
                        1024)) //可以通过自己的内存缓存实现
//                .memoryCache(new LruMemoryCache(2 * 1024 * 1024)) //可以通过自己的内存缓存实现
                .memoryCacheSize(2 * 1024 * 1024)
                .memoryCacheSizePercentage(13) // default
                .diskCacheSize(50 * 1024 * 1024) // 50 Mb sd卡(本地)缓存的最大值
//                .diskCacheFileNameGenerator(new Md5FileNameGenerator())// default为使用HASHCODE对UIL进行加密命名， 还可以用MD5(new Md5FileNameGenerator())加密
//              .diskCacheFileNameGenerator(new HashCodeFileNameGenerator())
//                .tasksProcessingOrder(QueueProcessingType.LIFO)//加密
//              .tasksProcessingOrder(QueueProcessingType.FIFO)// default
                .diskCacheFileCount(100) //缓存的文件数量
                .imageDecoder(new BaseImageDecoder(true)) // default
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                .imageDownloader(new BaseImageDownloader(getApplicationContext(), 5 * 1000,
                        30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间
                .writeDebugLogs() // Remove for release app
                .build();// 开始构建
        ImageLoader.getInstance().init(config);

    }
}
