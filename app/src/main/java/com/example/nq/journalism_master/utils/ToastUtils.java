package com.example.nq.journalism_master.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/3/31.
 */

public class ToastUtils {
    private static Toast toast;

    public static void show(String msg, Context context) {
        if (toast == null) {
            toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        }
        toast.setText(msg);
        toast.show();
    }
}
