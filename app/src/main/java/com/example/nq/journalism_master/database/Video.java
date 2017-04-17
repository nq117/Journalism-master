package com.example.nq.journalism_master.database;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/4/14.
 */

public class Video extends DataSupport {

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
