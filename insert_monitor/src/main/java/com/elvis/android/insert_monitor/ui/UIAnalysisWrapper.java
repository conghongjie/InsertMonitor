package com.elvis.android.insert_monitor.ui;


/**
 * Created by conghongjie on 2018/6/24.
 */

public class UIAnalysisWrapper implements IUIAnalysis {


    /**
     * 一个时间段只能保留一个实例对象
     */
    private static UIAnalysisWrapper nowAnalysisWrapper;

    public static void  setNowAnalysisWrapper(UIAnalysisWrapper wrapper) {
        nowAnalysisWrapper = wrapper;
    }

    public static UIAnalysisWrapper getNowAnalysisWrapper() {
        return nowAnalysisWrapper;
    }

    /**
     * 反射接口实现类
     */
    private static final String ADAPTER_CLASS = "com.elvis.android.insert_monitor.ui.UIAnalysisImpl";
    private IUIAnalysis impl;
    public void tryRegister(){
        try {
            Class<?> clz = Class.forName(ADAPTER_CLASS);
            Object o = clz.newInstance();
            if (o instanceof IUIAnalysis) {
                impl = (IUIAnalysis) o;
            }
        } catch (Throwable tr) {
            tr.printStackTrace();
        }
        if (impl!=null){
            impl.setNowAnalysis();
        }
    }
    public boolean isImplEnable(){
        return impl!=null;
    }


    /**
     * 包装实现接口
     */
    @Override
    public void setNowAnalysis() {
        // do nothing
    }

    @Override
    public void onData(String info){
        if (impl!=null){
            impl.onData(info);
        }
    }
}
