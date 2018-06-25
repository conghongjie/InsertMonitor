package com.elvis.android.insert_monitor.collect.base;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Debug;

import com.elvis.android.insert_monitor.obj.info.ThreadCpuInfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by conghongjie on 2018/6/25.
 */

public class BaseCollectUtils {


    //系统总时间片
    private static final int BUFFER_SIZE = 1000;//文件读取缓存大小
    public static long getCPU_total() {
        long cpuTotal = 0;
        try {
            BufferedReader cpuReader = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/stat")), BUFFER_SIZE);
            String cpuRate = cpuReader.readLine();
            if (cpuRate != null) {
                String[] cpuInfoArray = cpuRate.split(" ");
                if (cpuInfoArray.length >= 9) {
                    long user = Long.parseLong(cpuInfoArray[2]);
                    long nice = Long.parseLong(cpuInfoArray[3]);
                    long system = Long.parseLong(cpuInfoArray[4]);
                    long idle = Long.parseLong(cpuInfoArray[5]);
                    long ioWait = Long.parseLong(cpuInfoArray[6]);
                    cpuTotal = user + nice + system + idle + ioWait
                            + Long.parseLong(cpuInfoArray[7])
                            + Long.parseLong(cpuInfoArray[8]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cpuTotal;
    }
    //进程时间片
    public static long getCPU_app(int pid) {
        long cpuApp = 0;
        try {
            BufferedReader pidReader = new BufferedReader(new InputStreamReader(
                    new FileInputStream("/proc/" + pid + "/stat")), BUFFER_SIZE);
            String pidCpuRate = pidReader.readLine();
            if (pidCpuRate != null) {
                String[] pidCpuInfoList = pidCpuRate.split(" ");
                if (pidCpuInfoList.length >= 17) {
                    cpuApp = Long.parseLong(pidCpuInfoList[13])
                            + Long.parseLong(pidCpuInfoList[14])
                            + Long.parseLong(pidCpuInfoList[15])
                            + Long.parseLong(pidCpuInfoList[16]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cpuApp;
    }
    //进程所有线程时间片
    public static ArrayList<ThreadCpuInfo> getCPU_threads(int pid) {
        ArrayList<ThreadCpuInfo> threadCpuInfos = new ArrayList<>();
        File threadDir = new File("/proc/" + pid + "/task");
        if (!threadDir.exists()){
            return threadCpuInfos;
        }
        File[] threadFiles = threadDir.listFiles();
        for(File threadFile: threadFiles){
            try{
                File statFile = new File(threadFile,"stat");
                BufferedReader pidReader = new BufferedReader(new InputStreamReader(new FileInputStream(statFile)), BUFFER_SIZE);
                String line = pidReader.readLine();
                if (line != null) {
                    //找到第一个'('和最后一个')'
                    int firstQianKuoHao = 0;
                    int lastHouKuoHao = 0;
                    for(int k=0; k<line.length();k++){
                        if(line.charAt(k)=='(' && firstQianKuoHao==0 ){
                            firstQianKuoHao = k;
                        }
                        if(line.charAt(k)==')' && k>lastHouKuoHao){
                            lastHouKuoHao = k;
                        }
                    }
                    String threadId = line.substring(0,firstQianKuoHao-1);
                    String threadName = line.substring(firstQianKuoHao+1,lastHouKuoHao);
                    String lastLine = line.substring(lastHouKuoHao+2,line.length());
                    String[] pidCpuInfoList = lastLine.split(" ");
                    if (pidCpuInfoList.length >= 17) {
                        long threadCpp = Long.parseLong(pidCpuInfoList[11]) + Long.parseLong(pidCpuInfoList[12]);
                        ThreadCpuInfo threadCpuInfo = new ThreadCpuInfo(0);
                        threadCpuInfo.threadId = threadId;
                        threadCpuInfo.threadName = threadName;
                        threadCpuInfo.threadCpu = threadCpp;
                        threadCpuInfos.add(threadCpuInfo);
                        //cpuThreads = cpuThreads +threadId+":"+threadCpp+":"+threadName.replace(",","@@@").replace(":","%%%").replace("\n","")+",";
                    }
                }
            }catch (Exception e){
            }
        }
        return threadCpuInfos;
    }
    //进程总内存
    public static int getMemory_app(int pid, Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        int[] myMempid = new int[] { pid };
        Debug.MemoryInfo[] memoryInfo = am.getProcessMemoryInfo(myMempid);
        int memSize = memoryInfo[0].getTotalPss();
        //        dalvikPrivateDirty： The private dirty pages used by dalvik。
        //        dalvikPss ：The proportional set size for dalvik.
        //        dalvikSharedDirty ：The shared dirty pages used by dalvik.
        //        nativePrivateDirty ：The private dirty pages used by the native heap.
        //        nativePss ：The proportional set size for the native heap.
        //        nativeSharedDirty ：The shared dirty pages used by the native heap.
        //        otherPrivateDirty ：The private dirty pages used by everything else.
        //        otherPss ：The proportional set size for everything else.
        //        otherSharedDirty ：The shared dirty pages used by everything else.
        return memSize;
    }

}
