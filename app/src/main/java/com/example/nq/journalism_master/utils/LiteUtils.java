package com.example.nq.journalism_master.utils;

import android.content.Context;

import com.example.nq.journalism_master.database.News;
import com.example.nq.journalism_master.database.Video;

import org.litepal.tablemanager.Connector;

/**
 * Created by nq on 2017/4/16.
 */

public class LiteUtils {

    //存储
    public static void setLite(String id, Context context) {
        Connector.getDatabase();
        News news = new News();
        news.setName(id);
        news.save();
//        if (news.save()) {
//            Toast.makeText(context, "存储成功", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(context, "存储失败", Toast.LENGTH_SHORT).show();
//        }
    }

    //存储
    public static void setLiteVideo(String id, Context context) {
        Connector.getDatabase();
        Video video = new Video();
        video.setName(id);
        video.save();
//        if (video.save()) {
//            Toast.makeText(context, "存储成功", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(context, "存储失败", Toast.LENGTH_SHORT).show();
//        }
    }
}
