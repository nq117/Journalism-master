package com.example.nq.journalism_master.bean.Top;

import java.util.List;

/**
 * Created by Administrator on 2017/3/31.
 */

public class NewsModel {
    // 共同
    public int replyCount; //编号
    public String docid; //id
    public String title; //标题
    public String source; //某网
    public String imgsrc; //图片地址

    // 顶部
    public List<Ads> ads;
    public String photosetID; //地址ip

    // 多图
    public List<ImageModel> imgextra;

    // 普通
    public String url_3w;
    public String ptime;

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public List<Ads> getAds() {
        return ads;
    }

    public void setAds(List<Ads> ads) {
        this.ads = ads;
    }

    public String getPhotosetID() {
        return photosetID;
    }

    public void setPhotosetID(String photosetID) {
        this.photosetID = photosetID;
    }

    public List<ImageModel> getImgextra() {
        return imgextra;
    }

    public void setImgextra(List<ImageModel> imgextra) {
        this.imgextra = imgextra;
    }

    public String getUrl() {
        return url_3w;
    }

    public void setUrl(String url) {
        this.url_3w = url_3w;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

}

