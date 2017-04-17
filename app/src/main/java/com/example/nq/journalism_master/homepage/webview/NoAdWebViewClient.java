package com.example.nq.journalism_master.homepage.webview;

import android.content.Context;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Administrator on 2017/3/29.
 * NoAdWebViewClient 屏蔽广告
 */

public class NoAdWebViewClient extends WebViewClient{
    private String homeurl;
    private Context context;

    public NoAdWebViewClient(Context context,String homeurl){
        this.context= context;
        this.homeurl= homeurl;
    }

    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, String url){
        url= url.toLowerCase();
        if(!url.contains(homeurl)){
            if(!ADFilterTool.hasAd(context,url)){
                return super.shouldInterceptRequest(view,url);//正常加载
            }else{
                return new WebResourceResponse(null,null,null);//含有广告资源屏蔽请求
            }
        }else{
            return super.shouldInterceptRequest(view,url);
        }


    }
}
