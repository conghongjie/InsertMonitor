package com.elvis.android.insert_monitor.plugin.aspectjx;

import android.view.View;

import com.elvis.android.insert_monitor.collect.aspectjx.InflateCollector;
import com.elvis.android.insert_monitor.obj.info.InflateInfo;
import com.elvis.android.insert_monitor.utils.StackUtils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;


/**
 * Created by conghongjie on 2018/6/27.
 */

@Aspect
public class AspectJxInflate {

    @Around("call(void android.app.Activity.setContentView(int))")
    public void Activity_setContentView(ProceedingJoinPoint joinPoint) throws Throwable {
        if (InflateCollector.isStart()){
            long start = System.currentTimeMillis();
            joinPoint.proceed();
            long end = System.currentTimeMillis();
            if (end-start>InflateCollector.MAX_INFLATE_TIME){
                String resource="未知";
                try{
                    Object[] args = joinPoint.getArgs();
                    int resId = (int) args[0];
                    resource = InflateCollector.getContext().getResources().getResourceName(resId);
                }catch (Exception e){
                }
                InflateInfo inflateInfo = new InflateInfo(start);
                inflateInfo.methodName = InflateInfo.Activity_setContentView;
                inflateInfo.resource = resource;
                inflateInfo.stack = StackUtils.getStack(Thread.currentThread());
                inflateInfo.startTime = start;
                inflateInfo.endTime = end;
                InflateCollector.sendInfo(inflateInfo,false);
            }
        }else {
            joinPoint.proceed();
        }
        return;
    }


    @Around("call(android.view.View android.view.LayoutInflater.inflate(int,android.view.ViewGroup))")
    public View LayoutInflater_inflate(ProceedingJoinPoint joinPoint) throws Throwable {
        View result;
        if (InflateCollector.isStart()){
            long start = System.currentTimeMillis();
            result = (View) joinPoint.proceed();
            long end = System.currentTimeMillis();
            if (end-start>InflateCollector.MAX_INFLATE_TIME){
                String resource="未知";
                try{
                    Object[] args = joinPoint.getArgs();
                    int resId = (int) args[0];
                    resource = InflateCollector.getContext().getResources().getResourceName(resId);
                }catch (Exception e){
                }
                InflateInfo inflateInfo = new InflateInfo(start);
                inflateInfo.methodName = InflateInfo.LayoutInflater_inflate;
                inflateInfo.resource = resource;
                inflateInfo.stack = StackUtils.getStack(Thread.currentThread());
                inflateInfo.startTime = start;
                inflateInfo.endTime = end;
                InflateCollector.sendInfo(inflateInfo,false);
            }
        }else {
            result = (View) joinPoint.proceed();
        }
        return result;
    }




}
