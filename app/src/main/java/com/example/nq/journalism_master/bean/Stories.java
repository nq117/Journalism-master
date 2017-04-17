package com.example.nq.journalism_master.bean;

/**
 * Created by Administrator on 2017/3/30.
 */

public class Stories {

    /**
     * images : ["https://pic1.zhimg.com/v2-aa49b42673629c07ea02887613847574.jpg"]
     * type : 0
     * id : 9325768
     * ga_prefix : 033017
     * title : 村上春树说永远不要同情自己，但也许他错了
     */

    private int type;
    private int id;
    private String ga_prefix;
    private String title;
    private String[] images;

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

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }
}
