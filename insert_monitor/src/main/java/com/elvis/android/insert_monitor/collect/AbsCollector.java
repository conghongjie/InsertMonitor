package com.elvis.android.insert_monitor.collect;

import com.elvis.android.insert_monitor.obj.AbsInfo;

/**
 * Created by conghongjie on 2018/6/25.
 */

public abstract class AbsCollector {


    protected ISender sender;

    public AbsCollector(ISender sender){
        this.sender = sender;
    }


    public abstract boolean start();


    public abstract boolean stop();




    public interface ISender {

        void send(AbsInfo info, boolean isUpload);

    }
}
