package com.jt.systemarchitecture.networkengine.simple4;

import android.content.Context;

import com.jt.systemarchitecture.networkengine.simple3.HttpCallBack;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/28 0028.
 */

public class HttpUtils {
    private OkHttpRequest mHttpRequest;
    private final int TYPE_GET = 0x0011;
    private int mType = TYPE_GET;
    private Map<String,Object> mParams;
    private String mUrl;
    private Context mContext;

    private HttpUtils(Context context) {
        mHttpRequest = new OkHttpRequest();
        mParams = new HashMap<>();
        this.mContext = context;
    }

    public static HttpUtils with(Context context){
        return new HttpUtils(context);
    }

    public HttpUtils get(){
        mType = TYPE_GET;
        return this;
    }

    public HttpUtils param(String key,Object value){
        mParams.put(key,value);
        return this;
    }

    public HttpUtils url(String url){
        this.mUrl = url;
        return this;
    }

    public HttpUtils cache(boolean cache){
        return this;
    }

    public void request(){
        request(null);
    }

    public<T> void request(final HttpCallBack<T> callBack){
        mHttpRequest.get(mContext,mUrl,mParams,callBack,true);
    }

}
