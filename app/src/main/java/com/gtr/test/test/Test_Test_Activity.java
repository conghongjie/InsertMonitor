package com.gtr.test.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.elvis.android.lib.insert_monitor.R;

public class Test_Test_Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_test);


        Button button_aidl = (Button) findViewById(R.id.button_aidl);
        button_aidl.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //new TestAIDL().test(Test_Test_Activity.this);
            }
        });


        Button button_jni = (Button) findViewById(R.id.button_jni);
        button_jni.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                new TestJNI().test();
            }
        });

        Button button_file = (Button) findViewById(R.id.button_file);
        button_file.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                new TestFileOperation().test();
            }
        });
    }











}
