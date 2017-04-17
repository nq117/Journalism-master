package com.example.nq.journalism_master.database;

import com.example.nq.journalism_master.bean.Top.NewsModel;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by Administrator on 2017/4/14.
 */

public class News extends DataSupport {

    private int id;

    private String name;   //title名称

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
