package com.campuscircle.app.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @ClassName AppsDataSetting
 * @Description 暂时的储存
 * @Author SeanLim
 * @Date 2021-8-25 14:23
 * @E-mail linlin.1016@qq.com
 * @Version 1.0
 */
public class AppsDataSetting {
    private SharedPreferences etnSharedPreference;
    private static AppsDataSetting instance = null;
    private AppsDataSetting(){}
    public static AppsDataSetting getInstance() {
        synchronized (AppsDataSetting.class) {
            if (instance == null)
                instance = new AppsDataSetting();
        }
        return instance;
    }


    public void init(Context context) {
        if(etnSharedPreference == null)etnSharedPreference = context.getSharedPreferences("etnSharePrivatePreferences", Context.MODE_PRIVATE);

    }


    /**
     * 写到缓存文件
     * @param key
     * @param value String类型
     */
    public void write(String key , String value){

        if (key.equals("uid")){
            String s=value;
        }else {

        }

        SharedPreferences.Editor editor = etnSharedPreference.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * 写到缓存文件
     * @param key
     * @param value int类型
     */
    public void write(String key , int value){
        SharedPreferences.Editor editor = etnSharedPreference.edit();
        editor.putInt(key, value);
        editor.commit();
    }
    /**
     * 删除数据
     * @param key
     */
    public void remove(String key){
        SharedPreferences.Editor editor = etnSharedPreference.edit();
        editor.remove(key);
        editor.commit();
    }
    /**
     * 写道缓存文件
     * @param key
     * @param value boolean类型
     */
    public void write(String key, boolean value) {
        SharedPreferences.Editor editor = etnSharedPreference.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }
    /**
     * 写道缓存文件
     * @param key
     * @param value float类型
     */
    public void write(String key, float value) {
        SharedPreferences.Editor editor = etnSharedPreference.edit();
        editor.putFloat(key, value);
        editor.commit();
    }
    /**
     * 写道缓存文件
     * @param key
     * @param value float类型
     */
    public void apply(String key, float value) {
        SharedPreferences.Editor editor = etnSharedPreference.edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    /**
     * 读取缓存文件
     * @param key
     * @param defaultValue String类型
     */
    public String read(String key, String defaultValue){
        String value = etnSharedPreference.getString(key, defaultValue);
        return value;
    }
    /**
     * 读取缓存文件
     * @param key
     * @param defaultValue int类型
     */
    public int read(String key, int defaultValue){
        return etnSharedPreference.getInt(key,defaultValue);
    }
    /**
     * 读取缓存文件
     * @param key
     * @param defaultValue boolean类型
     */
    public boolean read(String key, Boolean defaultValue){
        return etnSharedPreference.getBoolean(key,defaultValue);
    }
    /**
     * 读取缓存文件
     * @param key
     * @param defaultValue float类型
     */
    public float read(String key, float defaultValue){
        return etnSharedPreference.getFloat(key,defaultValue);
    }
}
