package com.jt.systemarchitecture.networkengine.simple5;

import android.content.Context;

import java.util.Map;

/**
 * Created by Administrator on 2018/5/3 0003.
 *
 */

public interface InHttpRequest {
    /**
     *  get请求参数
     * @param context 上下文
     * @param url  接口地址
     * @param params  参数集合
     * @param callBack  调接口返回
     * @param cache 缓存
     * @param <T>
     */
    <T> void get(Context context, String url, Map<String,Object> params,
                 final HttpCallBack<T> callBack, final boolean cache);
}
