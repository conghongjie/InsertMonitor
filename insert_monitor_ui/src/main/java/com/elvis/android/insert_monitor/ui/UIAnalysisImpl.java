package com.elvis.android.insert_monitor.ui;


import com.elvis.android.insert_monitor.InsertMonitor;
import com.elvis.android.insert_monitor.obj.info.ActivityInfo;
import com.elvis.android.insert_monitor.obj.info.BaseInfo;
import com.elvis.android.insert_monitor.obj.info.BlockInfo;
import com.elvis.android.insert_monitor.obj.info.DBInfo;
import com.elvis.android.insert_monitor.obj.info.InflateInfo;
import com.elvis.android.insert_monitor.obj.info.SMInfo;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by conghongjie on 2018/6/24.
 */

public class UIAnalysisImpl implements IUIAnalysis {

    /**
     * instance
     */

    public static IUIAnalysis instance;

    @Override
    public void setInstance() {
        instance = this;
    }

    /**
     * callback
     */

    public interface UICallBack{
        void onSMChange();
        void onBaseChange();
        void onBlockChange();
        void onActivityChange();
        void onDBChange();
        void onInflateChange();
    }
    private static ArrayList<UICallBack> callBacks = new ArrayList<>();
    public static void addCallBack(UICallBack callBack){
        callBacks.add(callBack);
    }
    public static void removeCallBack(UICallBack callBack){
        callBacks.add(callBack);
    }
    private static void callSMChange(){
        for (int i=0;i<callBacks.size();i++){
            callBacks.get(i).onSMChange();
        }
    }
    private static void callBaseChange(){
        for (int i=0;i<callBacks.size();i++){
            callBacks.get(i).onBaseChange();
        }
    }
    private static void callBlockChange(){
        for (int i=0;i<callBacks.size();i++){
            callBacks.get(i).onBlockChange();
        }
    }
    private static void callActivityChange(){
        for (int i=0;i<callBacks.size();i++){
            callBacks.get(i).onActivityChange();
        }
    }
    private static void callDBChange(){
        for (int i=0;i<callBacks.size();i++){
            callBacks.get(i).onDBChange();
        }
    }
    private static void callViewBuildChange(){
        for (int i=0;i<callBacks.size();i++){
            callBacks.get(i).onInflateChange();
        }
    }


    /**
     * Data
     */
    public static ArrayList<SMInfo> smInfos = new ArrayList<>();
    private static void addSMInfo(SMInfo smInfo){
        smInfos.add(smInfo);
        if (smInfos.size()>300){
            smInfos.remove(0);
        }
    }
    public static ArrayList<SMInfo> getSmInfos() {
        return smInfos;
    }
    public static long getNowSM(){
        long sm = 0;
        if (smInfos.size()>0) {
            sm = smInfos.get(smInfos.size() - 1).sm;
        }
        return sm;
    }

    public static ArrayList<BaseInfo> baseInfos = new ArrayList<>();
    private static void addBaseInfo(BaseInfo baseInfo){
        baseInfos.add(baseInfo);
        if (baseInfos.size()>20){
            baseInfos.remove(0);
        }
    }
    public static ArrayList<BaseInfo> getBaseInfos() {
        return baseInfos;
    }
    public static double getCpu(){
        double cpu = 0;
        try {
            int size = baseInfos.size();
            if (size>=2){
                BaseInfo lastBaseInfo = baseInfos.get(size-2);
                BaseInfo thisBaseInfo = baseInfos.get(size-1);
                double processCpu =thisBaseInfo.processCpu-lastBaseInfo.processCpu;
                double systemCpu =thisBaseInfo.systemCpu-lastBaseInfo.systemCpu;
                cpu = systemCpu<=0?0:(processCpu*100/systemCpu);
            }
        }catch (Exception ignored){}
        cpu = (double) Math.round(cpu * 1000) / 1000;
        return cpu;
    }
    public static double getMemory(){
        double memory = 0;
        try {
            int size = baseInfos.size();
            if (size>=1){
                BaseInfo thisBaseInfo = baseInfos.get(size-1);
                memory = thisBaseInfo.processMemory/(double)1024;
            }
        }catch (Exception ignored){}
        memory = (double) Math.round(memory * 10) / 10;
        return memory;
    }
    public static double getFlow(){
        double flow = 0;
        try {
            int size = baseInfos.size();
            if (size>=1){
                BaseInfo thisBaseInfo = baseInfos.get(size-1);
                flow = (thisBaseInfo.flowUpload+thisBaseInfo.flowDownload)/(double)1024;
            }
        }catch (Exception ignored){}
        flow = (double) Math.round(flow * 100) / 100;
        return flow;
    }



    public static ArrayList<BlockInfo> blockInfos = new ArrayList<>();
    private static void addBlockInfo(BlockInfo blockInfo){
        blockInfos.add(blockInfo);
    }
    public static ArrayList<BlockInfo> getBlockInfos() {
        return blockInfos;
    }

    public static ArrayList<DBInfo> dbInfos = new ArrayList<>();
    private static void addDBInfo(DBInfo dbInfo){
        dbInfos.add(dbInfo);
    }
    public static ArrayList<DBInfo> getDBInfos() {
        return dbInfos;
    }

    public static ArrayList<InflateInfo> inflateInfos = new ArrayList<>();
    private static void addInflateInfo(InflateInfo inflateInfo){
        inflateInfos.add(inflateInfo);
    }
    public static ArrayList<InflateInfo> getInflateInfos() {
        return inflateInfos;
    }








    @Override
    public void onData(String info) {
        try {
            JSONObject jsonObject = new JSONObject(info);
            String dataType = jsonObject.getString("dataType");
            switch (dataType){
                case "ActivityInfo":
                    ActivityInfo activityInfo = InsertMonitor.getIJson().fromJson(info,ActivityInfo.class);
                    break;
                case "BaseInfo":
                    BaseInfo baseInfo = InsertMonitor.getIJson().fromJson(info,BaseInfo.class);
                    addBaseInfo(baseInfo);
                    callBaseChange();
                    break;
                case "BlockInfo":
                    BlockInfo blockInfo = InsertMonitor.getIJson().fromJson(info,BlockInfo.class);
                    addBlockInfo(blockInfo);
                    callBlockChange();
                    break;
                case "DBInfo":
                    DBInfo dbInfo = InsertMonitor.getIJson().fromJson(info,DBInfo.class);
                    addDBInfo(dbInfo);
                    callDBChange();
                    break;
                case "InflateInfo":
                    InflateInfo inflateInfo = InsertMonitor.getIJson().fromJson(info,InflateInfo.class);
                    addInflateInfo(inflateInfo);
                    callViewBuildChange();
                    break;
                case "SMInfo":
                    SMInfo smInfo = InsertMonitor.getIJson().fromJson(info,SMInfo.class);
                    addSMInfo(smInfo);
                    callSMChange();
                    break;
                default:
                    throw new Exception("未知类型数据："+(info==null?"null":info));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clearData() {
        smInfos.clear();
        baseInfos.clear();
        blockInfos.clear();
        dbInfos.clear();
        inflateInfos.clear();
    }

}
