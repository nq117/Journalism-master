package com.example.nq.journalism_master.utils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2017/3/28.
 */

public class ApiUtils {

    private final static String TAG = "ApiUtils";

    public static void Getjuhe(Callback.CacheCallback<String> callback, String key){
        String urlString = Constant.Url.Api+"?key="+key;
        RequestParams params = new RequestParams(urlString);
        x.http().get(params, callback);
    }

    public static void Postjuhe(Callback.CacheCallback<String> callback, String type, String key){
        String urlString = Constant.Url.Api+"?type="+type+"&key="+key;
        RequestParams params = new RequestParams(urlString);
        if (type != null) {
            params.addBodyParameter("type", type);
        }
        if (key != null) {
            params.addBodyParameter("key", key);
        }
        x.http().get(params, callback);
    }

    public static void Getzhihu(Callback.CacheCallback<String> callback){
        String urlString = Constant.Url.ZhihuApi+Constant.Common.Zhihu;
        RequestParams params = new RequestParams(urlString);
        x.http().get(params, callback);
    }

    public static void Getzhihuweb(Callback.CacheCallback<String> callback, String id){
        String urlString = Constant.Url.ZhihuApi+id;
        RequestParams params = new RequestParams(urlString);
        if (id != null) {
            params.addBodyParameter("id", id);
        }
        x.http().get(params, callback);
    }

    public static void GetFunnyVideo(Callback.CacheCallback<String> callback) {
        String urlString = Constant.Url.FunnyVideo;
        RequestParams params = new RequestParams(urlString);
        x.http().get(params, callback);
    }

    public static void GetRecVideo(Callback.CacheCallback<String> callback) {
        String urlString = Constant.Url.RecVideo;
        RequestParams params = new RequestParams(urlString);
        x.http().get(params, callback);
    }

    public static void GetHotVideo(Callback.CacheCallback<String> callback) {
        String urlString = Constant.Url.HotVideo;
        RequestParams params = new RequestParams(urlString);
        x.http().get(params, callback);
    }

    public static void GetTop (Callback.CacheCallback<String> callback) {
        String urlString =  Constant.Url.NEWS_DETAIL + Constant.Url.HEADLINE_TYPE + Constant.Url.HEADLINE_ID + Constant.Url.HEADLINB_PAGE;
        RequestParams params = new RequestParams(urlString);
        x.http().get(params, callback);
    }
}
