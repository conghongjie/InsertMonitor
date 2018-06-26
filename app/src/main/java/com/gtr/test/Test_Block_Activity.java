package com.gtr.test;

import android.app.Activity;
import android.os.Bundle;

import com.elvis.android.lib.insert_monitor.R;

public class Test_Block_Activity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_block);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    @Override
    protected void onResume() {
        super.onResume();

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
