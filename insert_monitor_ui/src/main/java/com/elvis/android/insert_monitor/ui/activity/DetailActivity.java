package com.elvis.android.insert_monitor.ui.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.elvis.android.insert_monitor.ui.R;
import com.elvis.android.insert_monitor.ui.fragment.detail.BlockFragment;
import com.elvis.android.insert_monitor.ui.fragment.detail.DBFragment;
import com.elvis.android.insert_monitor.ui.fragment.detail.InflateFragment;

/**
 * Created by conghongjie on 2018/7/4.
 */

public class DetailActivity extends Activity{


    ImageView img_back;
    TextView txt_title;


    FragmentManager fm = getFragmentManager();
    public static final String KEY_DETAIL = "detail_type";
    public static final int DETAIL_BLOCK = 1;
    public static final int DETAIL_DB = 2;
    public static final int DETAIL_INFLATE = 3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_monitor_detail_activity);

        img_back = (ImageView) findViewById(R.id.img_back);
        txt_title = (TextView) findViewById(R.id.txt_title);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent  = getIntent();
        if (intent!=null){
            int detailType = intent.getIntExtra(KEY_DETAIL,-1);

            switch (detailType){
                case DETAIL_BLOCK:
                    txt_title.setText("卡顿检测");
                    replaceFragment(new BlockFragment());
                    break;
                case DETAIL_DB:
                    txt_title.setText("DB检测");
                    replaceFragment(new DBFragment());
                    break;
                case DETAIL_INFLATE:
                    txt_title.setText("View检测");
                    replaceFragment(new InflateFragment());
                    break;
                default:
                    finish();
            }
        }else {
            finish();
        }
    }

    void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fragment_content, fragment);
        transaction.commit();
    }



    public static void startDetailActivty(Context context,int detail_type){
        Intent intent = new Intent(context,DetailActivity.class);
        intent.putExtra(KEY_DETAIL,detail_type);
        if (!(context instanceof Activity)){
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }




}
