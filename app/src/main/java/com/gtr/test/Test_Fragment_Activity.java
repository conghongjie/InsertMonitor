package com.gtr.test;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gtr.test.fragment.FragmentDemo2;
import com.elvis.android.lib.insert_monitor.R;

public class Test_Fragment_Activity extends Activity {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }

    FragmentTransaction transaction = getFragmentManager().beginTransaction();
    Fragment fragment;//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_fragment);



        new Handler().post(new Runnable() {
            @Override
            public void run() {
                Log.e("ELVIS","S");
            }
        });


        if (fragment == null) {//显示、加载Fragment
            // 如果fragment为空，则创建一个并添加到界面上
            fragment = new FragmentDemo2();
            transaction.add(R.id.sssssss, fragment);
            transaction.commit();
        } else {// 如果Fragment不为空，则直接将它显示出来
            transaction.show(fragment);
        }


        Button button = (Button) findViewById(R.id.button10);
       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (fragment!= null) { //隐藏 Fragment
                   Toast.makeText(Test_Fragment_Activity.this,"ssss", Toast.LENGTH_LONG).show();
                   FragmentTransaction transaction = getFragmentManager().beginTransaction();
                   transaction.hide(fragment);
                   transaction.commit();
               }
           }
       });


        Button button12  = (Button) findViewById(R.id.button12);
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Test_Fragment_Activity.this,Test_Block_Activity.class);
                startActivity(intent);
            }
        });

    }




}
