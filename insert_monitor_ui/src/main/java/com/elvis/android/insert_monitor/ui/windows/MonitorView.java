package com.elvis.android.insert_monitor.ui.windows;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.elvis.android.insert_monitor.obj.info.BaseInfo;
import com.elvis.android.insert_monitor.obj.info.BlockInfo;
import com.elvis.android.insert_monitor.obj.info.SMInfo;
import com.elvis.android.insert_monitor.ui.R;
import com.elvis.android.insert_monitor.ui.UIAnalysisImpl;

import java.util.ArrayList;

/**
 * Created by conghongjie on 2017/12/22.
 */

public class MonitorView extends LinearLayout {



    public MonitorView(Context context) {
        super(context);
        //初始化View
        initView(context);
    }


    //根布局
    View rootView;
    //数据布局
    TextView txt_value_sm;
    TextView txt_value_block;
    TextView txt_value_cpu;
    TextView txt_value_memory;
    TextView txt_value_warnings;

    void initView(final Context context){
        //rootView
        LayoutInflater mInflater = LayoutInflater.from(context);
        rootView = mInflater.inflate(R.layout.insert_monitor_window_view, null);
        addView(rootView);
        //views
        txt_value_sm = (TextView) rootView.findViewById(R.id.txt_value_sm);
        txt_value_block = (TextView) rootView.findViewById(R.id.txt_value_block);
        txt_value_cpu = (TextView) rootView.findViewById(R.id.txt_value_cpu);
        txt_value_memory = (TextView) rootView.findViewById(R.id.txt_value_memory);
        txt_value_warnings = (TextView) rootView.findViewById(R.id.txt_value_warnings);
        //
        txt_value_sm.setText(""+UIAnalysisImpl.getNowSM());
        txt_value_cpu.setText(""+UIAnalysisImpl.getCpu()*100+"%");
        txt_value_memory.setText(""+UIAnalysisImpl.getMemory());
        txt_value_block.setText(""+UIAnalysisImpl.getBlockInfos().size());
        int dbNum = UIAnalysisImpl.getDBInfos().size();
        int inflateNum = UIAnalysisImpl.getInflateInfos().size();
        txt_value_warnings.setText(""+(dbNum+inflateNum));
    }


    /**
     * 数据刷新
     */
    public void onShow(){
        UIAnalysisImpl.addCallBack(uiCallBack);
    }

    public void onHide(){
        UIAnalysisImpl.removeCallBack(uiCallBack);
    }

    Handler mainHandler = new Handler(Looper.getMainLooper());

    UIAnalysisImpl.UICallBack uiCallBack = new UIAnalysisImpl.UICallBack() {

        @Override
        public void onSMChange() {
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    txt_value_sm.setText(""+UIAnalysisImpl.getNowSM());
                }
            });
        }
        @Override
        public void onBaseChange() {
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    txt_value_cpu.setText(""+UIAnalysisImpl.getCpu()*100+"%");
                    txt_value_memory.setText(""+UIAnalysisImpl.getMemory());
                }
            });
        }

        @Override
        public void onBlockChange() {
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    txt_value_block.setText(""+UIAnalysisImpl.getBlockInfos().size());
                }
            });
        }

        @Override
        public void onActivityChange() {
            updateOthers();
        }

        @Override
        public void onDBChange() {
            updateOthers();
        }

        @Override
        public void onInflateChange() {
            updateOthers();
        }

        private void updateOthers(){
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    int dbNum = UIAnalysisImpl.getDBInfos().size();
                    int inflateNum = UIAnalysisImpl.getInflateInfos().size();
                    txt_value_warnings.setText(""+(dbNum+inflateNum));
                }
            });
        }


    };




    /**
     * 点击事件处理：
     */
    private OnClickListener onClickListener;
    @Override
    public void setOnClickListener(OnClickListener l) {
        this.onClickListener = l;
    }

    /**
     * 拖拽事件处理：
     */
    interface OnMoveListener{
        void onMove(int x, int y);
    }
    MonitorView.OnMoveListener onMoveListener;
    public void setOnMoveListener(MonitorView.OnMoveListener onMoveListener) {
        this.onMoveListener = onMoveListener;
    }


    /**
     * 触摸事件处理：
     */
    private float mTouchX;
    private float mTouchY;
    private float x;
    private float y;
    private float mStartX;
    private float mStartY;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //获取到状态栏的高度
        Rect frame = new Rect();
        getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        // 获取相对屏幕的坐标
        x = event.getRawX();
        y = event.getRawY() - statusBarHeight;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 获取相对View的坐标
                mTouchX = event.getX();
                mTouchY = event.getY();
                mStartX = x;
                mStartY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                // 刷新位置
                if (onMoveListener!=null){
                    onMoveListener.onMove((int) (x - mTouchX),(int) (y - mTouchY));
                }
                break;
            case MotionEvent.ACTION_UP:
                // 刷新位置
                if (onMoveListener!=null){
                    onMoveListener.onMove((int) (x - mTouchX),(int) (y - mTouchY));
                }
                // 判断是否需要处理点击事件
                if ((x - mStartX) < 5 && (y - mStartY) < 5) {
                    if(onClickListener !=null) {
                        onClickListener.onClick(this);
                    }
                }
                mTouchX = mTouchY = 0;
                break;
        }
        return true;
    }
}