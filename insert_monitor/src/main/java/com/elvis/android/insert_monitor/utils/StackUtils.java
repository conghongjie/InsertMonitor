package com.elvis.android.insert_monitor.utils;

import com.elvis.android.insert_monitor.obj.info.StackInfo;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by conghongjie on 2018/6/27.
 */

public class StackUtils {


    public static String getStack(Thread thread){
        StringBuilder stackStringBuilder = new StringBuilder();
        boolean isStart = false;
        for (StackTraceElement stackTraceElement : thread.getStackTrace()) {
            String code = stackTraceElement.toString();
            if (!isStart){
                if (!code.startsWith("dalvik.system.VMStack.getThreadStackTrace")
                        &&!code.startsWith("java.lang.Thread.getStackTrace")
                        &&!code.startsWith("com.elvis.android.insert_monitor")){
                    stackStringBuilder.append(stackTraceElement.toString()).append("\n");
                    isStart = true;
                }
            }else {
                stackStringBuilder.append(stackTraceElement.toString()).append("\n");
            }

        }
        return stackStringBuilder.toString();
    }




    public static String getBlockCode(ArrayList<StackInfo> stackInfos){
        HashMap<String,Integer> effectiveCodeTimes = new HashMap<>();
        for (int i = 0;i<stackInfos.size();i++){
            String effectiveCode = getBlockCode(stackInfos.get(i));
            Integer time = effectiveCodeTimes.get(effectiveCode);
            effectiveCodeTimes.put(effectiveCode,time==null?1:(time+1));
        }
        String mostEffectiveCode = "未知";
        int mostEffectiveCodeTime = 0;
        for (String code : effectiveCodeTimes.keySet()) {
            if (!code.equals("")){
                int time = effectiveCodeTimes.get(code);
                if (time>mostEffectiveCodeTime){
                    mostEffectiveCodeTime = time;
                    mostEffectiveCode = code;
                }
            }
        }
        return mostEffectiveCode;
    }

    private static String getBlockCode(StackInfo stackInfo){
        String[] stacks = stackInfo.stack.split("\n");
        for (int i = stacks.length-1;i>=0;i--){
            String code = stacks[i];
            if (!code.startsWith("android.")
                    &&!code.startsWith("java.")
                    &&!code.startsWith("libcore.")
                    &&!code.startsWith("com.android.internal.")
                    &&!code.contains("sun.")
                    &&!code.contains("com.elvis.")){
                return code;
            }
        }
        return "";
    }


    public static String getDBCode(String stack){
        String[] stacks = stack.split("\n");
        if (stacks.length>0){
            return stacks[0];
        }
        return "";
    }

    public static String getInflateCode(String stack){
        String[] stacks = stack.split("\n");
        if (stacks.length>0){
            return stacks[0];
        }
        return "";
    }


}
