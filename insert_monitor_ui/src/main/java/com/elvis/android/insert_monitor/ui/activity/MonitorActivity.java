package com.elvis.android.insert_monitor.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;

import com.elvis.android.insert_monitor.ui.R;
import com.elvis.android.insert_monitor.ui.UIAnalysisImpl;
import com.elvis.android.insert_monitor.ui.view.SwitchButton;
import com.elvis.android.insert_monitor.ui.windows.MonitorWindow;

/**
 * Created by conghongjie on 2018/7/3.
 */

public class MonitorActivity extends Activity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_monitor_activity_main);
        initView();
        UIAnalysisImpl.addCallBack(uiCallBack);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UIAnalysisImpl.removeCallBack(uiCallBack);
    }


    /**
     * Data
     */
    int lastCheckBlockNum = 0;
    int lastCheckDBNum = 0;
    int lastCheckInflateNum = 0;
    /**
     * View
     */
    SwitchButton windowSwitch;
    ItemView smItem;
    ItemView cpuItem;
    ItemView memoryItem;
    ItemView flowItem;
    ItemView blockItem;
    ItemView activityItem;
    ItemView dbItem;
    ItemView inflateItem;

    void initView(){
        windowSwitch = (SwitchButton) findViewById(R.id.window_switch);
        windowSwitch.setChecked(MonitorWindow.isShow);
        windowSwitch.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (isChecked && !MonitorWindow.isShow){
                    MonitorWindow.show(MonitorActivity.this);
                }else if (!isChecked && MonitorWindow.isShow){
                    MonitorWindow.hide();
                }
            }
        });
        smItem = buildItemView(R.id.item_sm);
        smItem.title.setText("流畅度:");
        cpuItem = buildItemView(R.id.item_cpu);
        cpuItem.title.setText("CPU:");
        memoryItem = buildItemView(R.id.item_memory);
        memoryItem.title.setText("内存:");
        flowItem = buildItemView(R.id.item_flow);
        flowItem.title.setText("流量:");

        blockItem = buildItemView(R.id.item_block);
        blockItem.title.setText("卡顿检测:");
        blockItem.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastCheckBlockNum = UIAnalysisImpl.getBlockInfos().size();
                blockItem.tip.setVisibility(View.GONE);
                blockItem.tip.setText("");
            }
        });
        activityItem = buildItemView(R.id.item_activity);
        activityItem.title.setText("页面测速:");
        dbItem = buildItemView(R.id.item_db);
        dbItem.title.setText("DB检测:");
        dbItem.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastCheckDBNum= UIAnalysisImpl.getDBInfos().size();
                dbItem.tip.setVisibility(View.GONE);
                dbItem.tip.setText("");
            }
        });
        inflateItem = buildItemView(R.id.item_view_build);
        inflateItem.title.setText("View检测:");
        inflateItem.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastCheckInflateNum = UIAnalysisImpl.getInflateInfos().size();
                inflateItem.tip.setVisibility(View.GONE);
                inflateItem.tip.setText("");
            }
        });


        smItem.txt.setText(UIAnalysisImpl.getNowSM()+"");
        cpuItem.txt.setText(UIAnalysisImpl.getCpu()*100+"%");
        memoryItem.txt.setText(UIAnalysisImpl.getMemory()+"");
        flowItem.txt.setText(UIAnalysisImpl.getFlow()+"");
        blockItem.txt.setText(UIAnalysisImpl.getBlockInfos().size()+"");
        int block_tip = UIAnalysisImpl.getBlockInfos().size()-lastCheckBlockNum;
        blockItem.tip.setVisibility(block_tip>0?View.VISIBLE:View.GONE);
        if (block_tip>0){
            blockItem.tip.setText(block_tip+"");
        }
        dbItem.txt.setText(UIAnalysisImpl.getDBInfos().size()+"");
        int db_tip = UIAnalysisImpl.getDBInfos().size()-lastCheckDBNum;
        dbItem.tip.setVisibility(db_tip>0?View.VISIBLE:View.GONE);
        if (db_tip>0){
            dbItem.tip.setText(db_tip+"");
        }
        inflateItem.txt.setText(UIAnalysisImpl.getInflateInfos().size()+"");
        int inflate_tip = UIAnalysisImpl.getInflateInfos().size()-lastCheckInflateNum;
        inflateItem.tip.setVisibility(inflate_tip>0?View.VISIBLE:View.GONE);
        if (inflate_tip>0){
            inflateItem.tip.setText(inflate_tip+"");
        }
    }

    /**
     * Data
     */

    Handler mainHandler = new Handler(Looper.getMainLooper());

    UIAnalysisImpl.UICallBack uiCallBack = new UIAnalysisImpl.UICallBack() {
        @Override
        public void onSMChange() {
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    smItem.txt.setText(UIAnalysisImpl.getNowSM()+"");
                }
            });
        }

        @Override
        public void onBaseChange() {
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                cpuItem.txt.setText(UIAnalysisImpl.getCpu()*100+"%");
                memoryItem.txt.setText(UIAnalysisImpl.getMemory()+"");
                flowItem.txt.setText(UIAnalysisImpl.getFlow()+"");
                }
            });
        }

        @Override
        public void onBlockChange() {
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                blockItem.txt.setText(UIAnalysisImpl.getBlockInfos().size()+"");
                int tip = UIAnalysisImpl.getBlockInfos().size()-lastCheckBlockNum;
                blockItem.tip.setVisibility(tip>0?View.VISIBLE:View.GONE);
                if (tip>0){
                    blockItem.tip.setText(tip+"");
                }
                }
            });
        }

        @Override
        public void onActivityChange() {

        }

        @Override
        public void onDBChange() {
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    dbItem.txt.setText(UIAnalysisImpl.getDBInfos().size()+"");
                    int tip = UIAnalysisImpl.getDBInfos().size()-lastCheckDBNum;
                    dbItem.tip.setVisibility(tip>0?View.VISIBLE:View.GONE);
                    if (tip>0){
                        dbItem.tip.setText(tip+"");
                    }
                }
            });
        }

        @Override
        public void onInflateChange() {
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    inflateItem.txt.setText(UIAnalysisImpl.getInflateInfos().size()+"");
                    int tip = UIAnalysisImpl.getInflateInfos().size()-lastCheckInflateNum;
                    inflateItem.tip.setVisibility(tip>0?View.VISIBLE:View.GONE);
                    if (tip>0){
                        inflateItem.tip.setText(tip+"");
                    }
                }
            });
        }
    };




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
