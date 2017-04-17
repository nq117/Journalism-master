package com.example.nq.journalism_master.bean;

/**
 * Created by Administrator on 2017/3/30.
 */

public class Top_stories {

    /**
     * image : https://pic2.zhimg.com/v2-daea62f9c2c38935d1b9bbeee8e97c0d.jpg
     * type : 0
     * id : 9325630
     * ga_prefix : 033015
     * title : 「徐静蕾老师，作为一位女导演，你觉得……」
     */

    private String image;
    private int type;
    private int id;
    private String ga_prefix;
    private String title;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
