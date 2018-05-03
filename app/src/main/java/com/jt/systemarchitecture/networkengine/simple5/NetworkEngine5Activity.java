package com.jt.systemarchitecture.networkengine.simple5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jt.systemarchitecture.R;
import com.jt.systemarchitecture.networkengine.simple3.ConstantValue;
import com.jt.systemarchitecture.networkengine.simple3.ResultEntity;

public class NetworkEngine5Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_engine5);

        HttpUtils.with(this).params("channel","头条").params("start","0").params("num","20")
                .httpRequest(new XutilsRequest())
                .url(ConstantValue.UrlConstant.HOME_DISCOVERY_URL)
                .get().request(new HttpCallBack<ResultEntity>() {

            @Override
            public void onSuccess(ResultEntity result) {
                Log.e("5", "onSuccess: "+result.getMsg()+","+result.getResult().getList().get(1).getTitle());
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}
