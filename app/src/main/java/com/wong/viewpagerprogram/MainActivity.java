package com.wong.viewpagerprogram;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

//    延迟跳转时间
    private final int DELAY_TIME = 2000;

    private final int HOME = 1000;
    private final int GUIDE = 1001;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case HOME:
                    goHome();
                    break;
                case GUIDE:
                    goGuide();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    /*
    * 初始化首页
    * */
    private void initView(){
        SharedPreferences sp = getSharedPreferences("setting",0);
        boolean isFirst = sp.getBoolean("isFirst",true);
        Message msg = new Message();
        if(isFirst){
            msg.what = GUIDE;
            mHandler.sendMessageDelayed(msg,DELAY_TIME);
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("isFirst",false).apply();
        }else {
            msg.what = HOME;
            mHandler.sendMessageDelayed(msg,DELAY_TIME);

        }
    }
    /*
    * 跳转到主页
    * */
    private void goHome(){
        Intent intent = new Intent(MainActivity.this,HomeActivity.class);
        startActivity(intent);
    }
    /*
    * 跳转到引导页
    * */
    private void goGuide(){
        Intent intent = new Intent(MainActivity.this,GuideActivity.class);
        startActivity(intent);
    }
}
