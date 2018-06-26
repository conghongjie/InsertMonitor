package com.elvis.android.insert_monitor.obj.info;

import com.elvis.android.insert_monitor.obj.AbsInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by conghongjie on 2018/6/21.
 */

public class BlockInfo extends AbsInfo {

    public long startTime;
    public long endTime;
    public int frameNum;//卡顿期间的帧数
    public ArrayList<StackInfo> stackInfos;

    public BlockInfo(long dataTime) {
        super(dataTime);
    }


    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public int getFrameNum() {
        return frameNum;
    }

    public void setFrameNum(int frameNum) {
        this.frameNum = frameNum;
    }

    public ArrayList<StackInfo> getStackInfos() {
        return stackInfos;
    }

    public void setStackInfos(ArrayList<StackInfo> stackInfos) {
        this.stackInfos = stackInfos;
    }
}
