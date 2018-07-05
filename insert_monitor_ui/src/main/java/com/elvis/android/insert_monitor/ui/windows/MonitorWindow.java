package com.elvis.android.insert_monitor.ui.windows;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.elvis.android.insert_monitor.ui.activity.MonitorActivity;

/**
 * Created by conghongjie on 2018/7/4.
 */

public class MonitorWindow {

    private static WindowManager windowManager = null;
    private static WindowManager.LayoutParams windowManagerParams = null;
    private static MonitorView monitorView = null;
    public static boolean isShow = false;

    public static void show(Context context){
        try {
            if (windowManager==null){
                windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            }
            if (windowManagerParams==null){
                windowManagerParams = new WindowManager.LayoutParams();
                windowManagerParams.type = WindowManager.LayoutParams.TYPE_PHONE; // 设置window type
                windowManagerParams.format = PixelFormat.RGBA_8888; // 设置图片格式，效果为背景透明
                // 设置Window flag
                windowManagerParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
                // 调整悬浮窗口至左上角，便于调整坐标
                windowManagerParams.gravity = Gravity.LEFT | Gravity.TOP;
                // 以屏幕左上角为原点，设置x、y初始值
                windowManagerParams.x = 0;
                windowManagerParams.y = 0;
                // 设置悬浮窗口长宽数据
                windowManagerParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
                windowManagerParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
            }
            if (monitorView == null){
                monitorView = new MonitorView(context);
                monitorView.setOnClickListener(doubleClickListener);
                monitorView.setOnMoveListener(new MonitorView.OnMoveListener() {
                    @Override
                    public void onMove(int x, int y) {
                        windowManagerParams.x = x;
                        windowManagerParams.y = y;
                        windowManager.updateViewLayout(monitorView, windowManagerParams);
                    }
                });
                // 显示图像
                windowManager.addView(monitorView, windowManagerParams);
                isShow = true;
                monitorView.onShow();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void hide(){
        try {
            if (windowManager!=null && monitorView !=null){
                monitorView.onHide();
                windowManager.removeView(monitorView);
                monitorView = null;
                isShow = false;
            }
        }catch (Exception e){
        }
    }

    /**
     * 双击事件
     */
    private static long lastClickTime = 0;
    private static View.OnClickListener doubleClickListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            long time = System.currentTimeMillis();
            Context context = v.getContext().getApplicationContext();
            if (time-lastClickTime<1000){
                lastClickTime = 0;
                Intent intent = new Intent(context,MonitorActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }else {
                lastClickTime = time;
                if (context!=null){
                    Toast.makeText(context,"双击可查看详情",Toast.LENGTH_LONG).show();
                }
            }
        }
    };

}
