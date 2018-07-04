package com.elvis.android.insert_monitor.ui;


import com.elvis.android.insert_monitor.obj.AbsInfo;

/**
 * Created by conghongjie on 2018/6/24.
 */

public class InsertMonitorUIWrapper implements InsertMonitorUI{


    /**
     * 单例模式
     */
    static InsertMonitorUIWrapper instance;
    public static InsertMonitorUIWrapper getInstance() {
        if (instance==null){
            synchronized (InsertMonitorUIWrapper.class){
                if (instance==null){
                    instance = new InsertMonitorUIWrapper();
                }
            }
        }
        return instance;
    }

    /**
     * 反射初始化
     */
    private static final String ADAPTER_CLASS = "com.elvis.android.insert_monitor.ui.InsertMonitorUIImpl";
    private InsertMonitorUI impl;
    private boolean isTryLoad = false;
    private void tryLoad(){
        if (!isTryLoad){
            synchronized (InsertMonitorUIWrapper.class){
                if (!isTryLoad){
                    try {
                        Class<?> clz = Class.forName(ADAPTER_CLASS);
                        Object o = clz.newInstance();
                        if (o instanceof InsertMonitorUI) {
                            impl = (InsertMonitorUI) o;
                        }
                    } catch (Throwable tr) {
                        tr.printStackTrace();
                    }
                    isTryLoad = true;
                }
            }
        }
    }
    public boolean isImplEnable(){
        tryLoad();
        return impl!=null;
    }


    /**
     * 包装实现接口
     */
    public void onData(AbsInfo info){
        tryLoad();
        if (impl!=null){
            impl.onData(info);
        }
    }



}
