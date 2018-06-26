package com.gtr.test;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.elvis.android.lib.insert_monitor.R;

public class Test_Bitmap_Activity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_bitmap);

        findViewById(R.id.v1).setBackgroundDrawable(getResources().getDrawable(R.drawable.big_img));

        ImageView iv1 = (ImageView) findViewById(R.id.iv1);
        iv1.setImageResource(R.drawable.big_img);

        ImageView iv2 = (ImageView) findViewById(R.id.iv2);
        iv2.setImageDrawable(getResources().getDrawable(R.drawable.big_img));

        ImageView iv3 = (ImageView) findViewById(R.id.iv3);
        iv3.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.big_img));
    }
}
