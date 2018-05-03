package com.jt.systemarchitecture.networkengine.simple5;

/**
 * Created by Administrator on 2018/4/28 0028.
 * 抽象类  调接口成功 失败方法
 */

public abstract class HttpCallBack<T> {
    //返回可直接操作的对象
    public abstract void onSuccess(T result);

    public abstract void onFailure(Exception e);
}
