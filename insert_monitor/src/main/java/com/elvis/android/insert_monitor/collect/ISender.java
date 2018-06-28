package com.elvis.android.insert_monitor.collect;

import com.elvis.android.insert_monitor.obj.AbsInfo;

/**
 * Created by conghongjie on 2018/6/28.
 */

public interface ISender {

    void send(AbsInfo info, boolean isUpload);

}