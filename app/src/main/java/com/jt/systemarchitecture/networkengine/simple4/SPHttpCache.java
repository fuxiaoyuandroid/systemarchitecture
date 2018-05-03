package com.jt.systemarchitecture.networkengine.simple4;

import com.jt.systemarchitecture.networkengine.simple3.PreferencesUtil;

/**
 * Created by Administrator on 2018/4/28 0028.
 */

public class SPHttpCache {
    public void saveCache(String finalUrl,String resultJson){
        PreferencesUtil.getInstance().saveParam(finalUrl,resultJson);
    }

    public String getCache(String finalUrl){
        return (String) PreferencesUtil.getInstance().getObject(finalUrl);
    }
}
