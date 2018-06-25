package com.elvis.android.insert_monitor.data;

import android.os.Handler;
import android.os.HandlerThread;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

/**
 * 负责将数据本地持久化
 *
 * Created by conghongjie on 2018/6/20.
 */

public class DataSaver {


//    /**
//     * 写
//     * @param data
//     */
//    public static void write(final IJson data){
//        initHandler();
//        if (handler==null){
//            return;
//        }
//        handler.post(new Runnable() {
//            @Override
//            public void run() {
//                initBufferedWriter();
//                if (bufferedWriter==null){
//                    return;
//                }
//                try {
//                    bufferedWriter.write(data.toJson().toString()+"\n");
//                    bufferedWriter.flush();
//                }catch (Exception e){
//                }
//            }
//        });
//    }
//
//
//    /**
//     * 读
//     * @param packageName
//     * @param appstartTime
//     */
//    public static void read(String packageName,long appstartTime){
//
//    }
//
//
//    //唯一工作线程
//    private static HandlerThread handlerThread;
//    private static Handler handler;
//    private static void initHandler(){
//        if (handler==null){
//            synchronized(DataSaver.class){
//                if (handler==null){
//                    handlerThread = new HandlerThread("DataLocalHandlerThread");
//                    handlerThread.start();
//                    handler = new Handler(handlerThread.getLooper());
//                }
//            }
//        }
//    }
//
//    //BufferedWriter
//    private static BufferedWriter bufferedWriter;
//    private static void initBufferedWriter(){
//        try {
//            File dir = new File(IMLog.gtrDirPath+File.separator+ IMLog.packageName);
//            if (!dir.exists()){
//                dir.mkdirs();
//            }
//            File file = new File(dir, IMLog.appStartTime+".txt");
//            if (!file.exists()){
//                file.createNewFile();
//            }
//            bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true),"utf-8"));
//        }catch (Exception e){
//        }
//    }


}
