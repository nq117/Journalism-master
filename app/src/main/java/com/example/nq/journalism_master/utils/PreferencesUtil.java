package com.example.nq.journalism_master.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.Map;


public class PreferencesUtil {
    private Context context = null;
    private SharedPreferences preferences = null;
    private String Uid;
    private boolean stopService = false;

    public PreferencesUtil(Context ctx) {
        context = ctx;
        preferences = initPerferences();
    }


    private SharedPreferences initPerferences() {
        if (null == context) {
            return null;
        }
        SharedPreferences per = context.getSharedPreferences(Constant.Common.PREFERENCES,
                Context.MODE_PRIVATE);
        return per;
    }

    public SharedPreferences getPreferences() {
        return preferences;
    }

    public boolean setString(String key, String val) {
        return preferences.edit().putString(key, val).commit();
    }

    public String getString(String key, String defaultValue) {
        return preferences.getString(key, defaultValue);
    }

    public boolean setBoolean(String key, boolean val) {
        return preferences.edit().putBoolean(key, val).commit();
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return preferences.getBoolean(key, defaultValue);
    }

    public boolean setFloat(String key, float val) {
        return preferences.edit().putFloat(key, val).commit();
    }

    public float getFloat(String key, float defaultValue) {
        return preferences.getFloat(key, defaultValue);
    }

    public boolean setInt(String key, int val) {
        return preferences.edit().putInt(key, val).commit();
    }

    public int getInt(String key, int defaultValue) {
        return preferences.getInt(key, defaultValue);
    }

    public boolean setLong(String key, long val) {
        return preferences.edit().putLong(key, val).commit();
    }

    public long getLong(String key, long defaultValue) {
        return preferences.getLong(key, defaultValue);
    }


    public Map<String, ?> getAll() {
        return preferences.getAll();
    }

    //获取token
    public String getToken() {
        return getString("token", "");
    }

    public void setToken(String token){
        setString("token",token);
    }

    //获取用户头像
    public String getImage_url() {
        return getString("userhead", "");
    }

    //获取用户名
    public String getUserName() {
        return getString("username", "");
    }

    //获取登录的手机号
    public String getPhone() {
        return getString("userphone", "");
    }

    //用户是否登录
    public boolean ifUserIsLogined() {
        return getBoolean("isuserislogined", false);
    }
    //保存昵称
    public void setNickName(String nickname){
        setString("username",nickname);
    }
    public String getNickName(){
        return getString("username",null);
    }
    //设置登录状态
    public void isUserLogined(boolean isLogin) {
        setBoolean("isuserislogined", isLogin);
    }
    public void clear() {
        Editor editor = preferences.edit();
        Map<String, ?> map = preferences.getAll();
        for (Map.Entry<String, ?> e : map.entrySet()) {
            editor.remove(e.getKey());
            editor.commit();
        }
    }

    public void clearInfo(String key) {
        Editor editor = preferences.edit();
        editor.remove(key);
        editor.commit();
    }


}
