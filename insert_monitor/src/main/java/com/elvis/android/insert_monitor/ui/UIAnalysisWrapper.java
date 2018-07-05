package com.elvis.android.insert_monitor.ui;


import com.elvis.android.insert_monitor.obj.AbsInfo;

/**
 * Created by conghongjie on 2018/6/24.
 */

public class UIAnalysisWrapper implements IUIAnalysis {


    /**
     * 单例模式
     */
    static UIAnalysisWrapper instance;
    public static UIAnalysisWrapper getInstance() {
        if (instance==null){
            synchronized (UIAnalysisWrapper.class){
                if (instance==null){
                    instance = new UIAnalysisWrapper();
                }
            }
        }
        return instance;
    }

    /**
     * 反射初始化
     */
    private static final String ADAPTER_CLASS = "com.elvis.android.insert_monitor.ui.UIAnalysisImpl";
    private IUIAnalysis impl;
    private boolean isTryLoad = false;
    private void tryLoad(){
        if (!isTryLoad){
            synchronized (UIAnalysisWrapper.class){
                if (!isTryLoad){
                    try {
                        Class<?> clz = Class.forName(ADAPTER_CLASS);
                        Object o = clz.newInstance();
                        if (o instanceof IUIAnalysis) {
                            impl = (IUIAnalysis) o;
                            impl.setInstance();
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

    @Override
    public void setInstance() {
        // do nothing
    }

    @Override
    public void onData(String info){
        tryLoad();
        if (impl!=null){
            impl.onData(info);
        }
    }

    @Override
    public void clearData() {
        tryLoad();
        if (impl!=null){
            impl.clearData();
        }
    }
}
