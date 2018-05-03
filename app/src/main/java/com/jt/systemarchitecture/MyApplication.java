package com.jt.systemarchitecture;

import android.app.Application;

import com.jt.systemarchitecture.networkengine.simple3.PreferencesUtil;
import com.jt.systemarchitecture.networkengine.simple5.HttpUtils;
import com.jt.systemarchitecture.networkengine.simple5.OkHttpRequest;

import org.xutils.x;

/**
 * Created by Administrator on 2018/5/3 0003.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true); //是否输出debug日志，开启debug会影响性能。
        PreferencesUtil.getInstance().init(this);
        HttpUtils.initInHttpRequest(new OkHttpRequest());
    }
}
