package com.elvis.android.insert_monitor.utils;

/**
 * Created by conghongjie on 2018/7/6.
 */

public class TimeUtils {



    public static String formatToSecond(long ms) {
        int ss = 1000;
        int mi = ss * 60;
        int hh = mi * 60;
        int dd = hh * 24;

        long day = ms / dd;
        long hour = (ms - day * dd) / hh;
        long minute = (ms - day * dd - hour * hh) / mi;
        double second = (ms - day * dd - hour * hh - minute * mi) / (double)ss;

        String strDay = day==0?"":((day < 10 ? "0" + day : "" + day)+"天");
        String strHour = hour==0?"":((hour < 10 ? "0" + hour : "" + hour)+"时");
        String strMinute = minute==0?"":((minute < 10 ? "0" + minute : "" + minute)+"分");
        String strSecond = second==0?"":((second < 10 ? "0" + second : "" + second)+"秒");

        return strDay+strHour+strMinute+strSecond;
    }
}
