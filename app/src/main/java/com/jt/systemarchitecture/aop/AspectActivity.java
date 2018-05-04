package com.jt.systemarchitecture.aop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jt.systemarchitecture.MainActivity;
import com.jt.systemarchitecture.R;

public class AspectActivity extends AppCompatActivity {
    private Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aspect);
        mButton = findViewById(R.id.aop_button);
    }
    @CheckNet
    public void aopClick(View view) {
        /*Toast.makeText(this,"aop",Toast.LENGTH_SHORT).show();
        if (CheckNetUtil.isNetworkAvailable(this)) {
            startActivity(new Intent(this, MainActivity.class));
        }*/
        startActivity(new Intent(this, MainActivity.class));
    }
}
