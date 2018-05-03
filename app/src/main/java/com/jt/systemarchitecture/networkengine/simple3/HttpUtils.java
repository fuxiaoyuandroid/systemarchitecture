package com.jt.systemarchitecture.networkengine.simple3;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;

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

public class HttpUtils  {

    private HttpUtils() {

    }

    public static <T> void get(Context context, String url, Map<String,Object> params,
                               final HttpCallBack<T> callBack, final boolean cache){
        OkHttpClient mOkHttpClient = new OkHttpClient();
        //公共参数
        params.put("appkey","76253a4c8656d647");

        final String jointUrl = Utils.jointParams(url,params);
        //缓存问题
       /* final String cacheJson = (String) PreferencesUtil.getInstance().getParam(jointUrl,"");

        if (cache && !TextUtils.isEmpty(cacheJson)){
            Gson gson = new Gson();
            T objResult = (T) gson.fromJson(cacheJson,Utils.analysisClazzInfo(callBack));
            callBack.onSuccess(objResult);
        }*/
        Request.Builder builder = new Request.Builder().url(jointUrl).tag(context);
        final Request request = builder.build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onFailure(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String resultJson = response.body().string();
                Log.d("tag", "onResponse: "+resultJson);
               /* if (cache && resultJson.equals(cacheJson)){
                    return;
                }*/
                Gson gson = new Gson();
                T objResult = (T) gson.fromJson(resultJson,Utils.analysisClazzInfo(callBack));
                callBack.onSuccess(objResult);
                if (cache){
                    PreferencesUtil.getInstance().saveParam(jointUrl,resultJson);
                }
            }
        });


    }
}
