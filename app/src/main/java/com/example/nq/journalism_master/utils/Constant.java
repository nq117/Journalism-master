package com.example.nq.journalism_master.utils;

/**
 * Created by Administrator on 2017/3/28.
 */

public class Constant {

    /**
     *top(头条，默认),shehui(社会),guonei(国内),guoji(国际),yule(娱乐)
     * ,tiyu(体育)junshi(军事),keji(科技),caijing(财经),shishang(时尚)
     *
     */

    public class Common {
        public final static String TAG="Journalism";
        public final static String PREFERENCES = TAG;
        public final static String AppKey = "67083ff3072a44bd675437a0ecb09bf0";
        public final static String Zhihu = "latest";
    }

    public static class Url {
        //接口地址
        public final static String ZhihuApi = "http://news-at.zhihu.com/api/3/news/";
        public final static String Api = "http://v.juhe.cn/toutiao/index";
        public final static String ApiSH = "shehui";
        public final static String ApiGN = "guonei";
        public final static String ApiGJ = "guoji";
        public final static String ApiYL = "yule";
        public final static String ApiTY = "tiyu";
        public final static String ApiJS = "junshi";
        public final static String ApiKJ = "keji";
        public final static String ApiCJ = "caijing";
        public final static String ApiSS = "shishang";

        // 新闻详情
        public final static String HOST = "http://c.m.163.com/";
        public final static String NEWS_DETAIL = HOST + "nc/article/";
        // 头条
        public final static String HEADLINE_TYPE = "headline/";
        public final static String HEADLINE_ID = "T1348647909107/";
        public final static String HEADLINB_PAGE = "0-20.html";

        //视频
        public final static String Hot = "V9LG4B3A0";  //热点
        public final static String Rec = "V9LG4CHOR";   //娱乐
        public final static String Funny = "V9LG4E6VR";  //搞笑
        public final static String Video = "http://c.m.163.com/";
        public final static String FunnyVideo = Video + "nc/video/list/" + Funny + "/n/0-20.html";

        public final static String RecVideo = Video + "nc/video/list/" + Rec + "/n/0-20.html";

        public final static String HotVideo = Video + "nc/video/list/" + Hot + "/n/0-20.html";

        public final static String TOUCH_HEAD = "http://3g.163.com/touch/article.html?docid=";
        public final static String TOUCH_END = "&version=dd";

        //照片集
        public final static String PHOTO_SET = HOST + "photo/api/set/";
    }
}
