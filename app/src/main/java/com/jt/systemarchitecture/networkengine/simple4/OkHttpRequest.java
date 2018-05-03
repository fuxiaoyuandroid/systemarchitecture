package com.jt.systemarchitecture.networkengine.simple4;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.jt.systemarchitecture.networkengine.simple3.HttpCallBack;
import com.jt.systemarchitecture.networkengine.simple3.Utils;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/4/28 0028.
 */

public class OkHttpRequest {
    private SPHttpCache mHttpCache;

    public OkHttpRequest() {
        mHttpCache = new SPHttpCache();
    }

    public <T> void get(Context context, String url, Map<String,Object> params, final HttpCallBack<T> callBack, final boolean cache){
        OkHttpClient mOkHttpClient = new OkHttpClient();
        //公共参数
        params.put("appkey","76253a4c8656d647");
        final String jointUrl = Utils.jointParams(url,params);
        final String cacheJson = mHttpCache.getCache(jointUrl);
        if (cache && !TextUtils.isEmpty(cacheJson)){
            Gson gson = new Gson();
            T objResult = (T) gson.fromJson(cacheJson,Utils.analysisClazzInfo(callBack));
            callBack.onSuccess(objResult);
        }
        Request.Builder mRequest = new Request.Builder();
        final Request request = mRequest.build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onFailure(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String resultJson = response.body().string();
                if (resultJson.equals(cacheJson) && cache){
                    return;
                }
                Gson gson = new Gson();
                T objResult = (T) gson.fromJson(resultJson,Utils.analysisClazzInfo(callBack));
                callBack.onSuccess(objResult);
                if (cache){
                    mHttpCache.saveCache(jointUrl,resultJson);
                }
            }
        });
    }

}
