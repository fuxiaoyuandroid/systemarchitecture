package com.jt.systemarchitecture.networkengine.simple3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jt.systemarchitecture.R;

import java.util.HashMap;
import java.util.Map;

public class NetworkEngineActivity extends AppCompatActivity {
    private static final String TAG = "NetworkEngineActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_engine);
        /********************访问网络开始*******************/
        Map<String, Object> params = new HashMap<>();
        params.put("channel","头条");
        params.put("start","0");
        params.put("num","20");

        HttpUtils.get(this, ConstantValue.UrlConstant.HOME_DISCOVERY_URL, params, new HttpCallBack<ResultEntity>() {

            @Override
            public void onSuccess(ResultEntity result) {
                if (result.getMsg().equals("ok")){
                    Log.d(TAG, "onSuccess: 1111");
                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        },true);
    }
}
