package com.elvis.android.insert_monitor.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.elvis.android.insert_monitor.ui.R;

/**
 * Created by conghongjie on 2018/7/3.
 */

public class MonitorActivity extends Activity{




    ItemView smItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor);


        smItem = buildItemView(R.id.item_sm);
    }


    private class ItemView {
        View root;
        TextView title;
        TextView txt;
        TextView tip;
    }
    public ItemView buildItemView(int resId){
        ItemView itemHolder = new ItemView();
        itemHolder.root = findViewById(resId);
        itemHolder.title = (TextView) itemHolder.root.findViewById(R.id.title);
        itemHolder.txt = (TextView) itemHolder.root.findViewById(R.id.txt);
        itemHolder.tip = (TextView) itemHolder.root.findViewById(R.id.tip);
        return itemHolder;
    }

}
