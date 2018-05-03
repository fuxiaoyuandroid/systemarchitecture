package com.jt.systemarchitecture.networkengine.simple5;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/3 0003.
 * 面向对象的六大原则
 * 单一职责原则
 * 开闭原则
 * 里氏替换原则
 * 依赖倒置原则
 * 接口隔离原则
 *
 */

public class HttpUtils {
    private Context mContext;
    private String mUrl;
    private final int TYPE_GET = 0x0011,TYPE_POST = 0x0022;
    private int mType = TYPE_GET;
    private Map<String,Object> mParams;
    private InHttpRequest mInHttpRequest;
    private static InHttpRequest mInit;
    public HttpUtils(Context mContext) {
        mInHttpRequest = new OkHttpRequest();
        mParams = new HashMap<>();
        this.mContext = mContext;
    }

    /**
     * 创建对象
     * @param context
     * @return
     */
    public static HttpUtils with(Context context){
        return new HttpUtils(context);
    }

    public HttpUtils get(){
        mType = TYPE_GET;
        return this;
    }
    /**
     * 传地址
     * @param url
     * @return
     */
    public HttpUtils url(String url){
        mUrl = url;
        return this;
    }

    /**
     * 传参数
     * @param key
     * @param value
     * @return
     */
    public HttpUtils params(String key,Object value){
        mParams.put(key,value);
        return this;
    }

    /**
     * 请求
     * @return
     */
    public <T> void request(){
        request(null);
    }

    public <T> void request(HttpCallBack<T> callBack){
        if (mInHttpRequest == null){
            mInHttpRequest = mInit;
        }
        //此处要做异常判断
        mInHttpRequest.get(mContext,mUrl,mParams,callBack,true);
    }

    /**
     * 设置
     * @param inHttpRequest
     * @return
     */
    public HttpUtils httpRequest(InHttpRequest inHttpRequest){
        mInHttpRequest = inHttpRequest;
        return this;
    }

    /**
     * 初始化
     * @param inHttpRequest
     */
    public static void initInHttpRequest(InHttpRequest inHttpRequest) {
        mInit = inHttpRequest;
    }
}
