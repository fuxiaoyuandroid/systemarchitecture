package com.jt.systemarchitecture.networkengine.simple5;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.jt.systemarchitecture.networkengine.simple3.Utils;
import com.jt.systemarchitecture.networkengine.simple4.SPHttpCache;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/5/3 0003.
 */

public class OkHttpRequest implements InHttpRequest {
    private SPHttpCache mHttpCache;

    public OkHttpRequest() {
        mHttpCache = new SPHttpCache();
    }

    @Override
    public <T> void get(Context context, String url, Map<String, Object> params, final HttpCallBack<T> callBack, final boolean cache) {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        //公共参数
        params.put("appkey","76253a4c8656d647");
        //Url拼接参数
        final String jointUrl = Utils.jointParams(url,params);
        //从缓存中取数据
        /*final String cacheJson = mHttpCache.getCache(jointUrl);
        if (cache && !TextUtils.isEmpty(cacheJson)){
            Gson gson = new Gson();
            T obj = (T) gson.fromJson(cacheJson,Utils.analysisClazzInfo(callBack));
            callBack.onSuccess(obj);
        }*/

        Request.Builder builder = new Request.Builder().url(jointUrl).tag(context);

        Request request = builder.build();

        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onFailure(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String resultJson = response.body().string();

               /* if (cache && resultJson.equals(cacheJson)) {
                    return;
                }*/
                Gson gson = new Gson();
                T obj = (T) gson.fromJson(resultJson,Utils.analysisClazzInfo(callBack));
                callBack.onSuccess(obj);
                /*if (cache){
                    mHttpCache.saveCache(jointUrl,resultJson);
                }*/
            }
        });

    }
}
