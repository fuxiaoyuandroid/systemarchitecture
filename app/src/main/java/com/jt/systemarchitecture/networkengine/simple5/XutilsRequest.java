package com.jt.systemarchitecture.networkengine.simple5;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.jt.systemarchitecture.networkengine.simple3.Utils;
import com.jt.systemarchitecture.networkengine.simple4.SPHttpCache;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Map;

/**
 * Created by Administrator on 2018/5/3 0003.
 */

public class XutilsRequest implements InHttpRequest {
    private SPHttpCache mHttpCache;

    public XutilsRequest() {
        mHttpCache = new SPHttpCache();
    }

    @Override
    public <T> void get(Context context, String url, Map<String, Object> params, final HttpCallBack<T> callBack, boolean cache) {
        params.put("appkey","76253a4c8656d647");

        RequestParams requestParams = new RequestParams(url);
        //遍历参数
        for (Map.Entry<String,Object> entry:params.entrySet()){
            requestParams.addQueryStringParameter(entry.getKey(), String.valueOf(entry.getValue()));
        }

        x.http().get(requestParams, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                Log.e("5", "onSuccess: "+result);
                Gson gson = new Gson();
                T obj = (T) gson.fromJson(result,Utils.analysisClazzInfo(callBack));
                callBack.onSuccess(obj);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}
