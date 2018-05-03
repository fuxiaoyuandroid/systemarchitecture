package com.jt.systemarchitecture;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.jt.systemarchitecture.networkengine.simple5.NetworkEngine5Activity;


/**
 * 20180427 系统架构
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 跳转
     * @param view
     */
    public void otherActivity(View view) {
        startActivity(new Intent(this, NetworkEngine5Activity.class));
    }
}
