package com.jt.systemarchitecture.networkengine.simple4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jt.systemarchitecture.R;
import com.jt.systemarchitecture.networkengine.simple3.ConstantValue;
import com.jt.systemarchitecture.networkengine.simple3.HttpCallBack;
import com.jt.systemarchitecture.networkengine.simple3.ResultEntity;

import java.util.HashMap;
import java.util.Map;

public class NetworkEngine4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_engine4);
        Map<String,Object> params = new HashMap<>();
        params.put("channel","头条");
        params.put("start","0");
        params.put("num","20");
        HttpUtils.with(this).url(ConstantValue.UrlConstant.HOME_DISCOVERY_URL).
                get().param("channel","头条").
                cache(true).param("start","0").param("num","20")
                .request(new HttpCallBack<ResultEntity>() {

            @Override
            public void onSuccess(ResultEntity result) {

            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}
