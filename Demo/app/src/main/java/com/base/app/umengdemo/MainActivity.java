package com.base.app.umengdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.base.app.umeng.push.PushHelper;
import com.umeng.push.demo.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PushHelper.init(App.appContext);
    }
}
