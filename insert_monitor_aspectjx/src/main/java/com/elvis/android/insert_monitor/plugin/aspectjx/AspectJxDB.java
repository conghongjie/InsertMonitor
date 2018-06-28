package com.elvis.android.insert_monitor.plugin.aspectjx;

import android.database.sqlite.SQLiteDatabase;

import com.elvis.android.insert_monitor.obj.info.DBInfo;
import com.elvis.android.insert_monitor.collect.aspectjx.IOCollector;
import com.elvis.android.insert_monitor.plugin.aspectjx.utils.ThreadUtils;
import com.elvis.android.insert_monitor.utils.StackUtils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * Created by conghongjie on 2018/6/27.
 */

@Aspect
public class AspectJxDB {





    @Around("call(android.database.sqlite.SQLiteDatabase android.database.sqlite.SQLiteOpenHelper.getWritableDatabase())")
    public SQLiteDatabase onDBGetWritableDatabase(ProceedingJoinPoint joinPoint) throws Throwable {
        SQLiteDatabase result = null;
        //检测是否在主线程
        if (ThreadUtils.isMainThread()){
            long start = System.currentTimeMillis();
            result = (SQLiteDatabase) joinPoint.proceed();
            long end = System.currentTimeMillis();
            DBInfo dbInfo = new DBInfo(start);
            dbInfo.startTime = start;
            dbInfo.endTime = end;
            dbInfo.method = "getWritableDatabase()";
            dbInfo.stack = StackUtils.getStack(Thread.currentThread());
            dbInfo.sql = "";
            IOCollector.sendInfo(dbInfo,false);
        }else {
            result = (SQLiteDatabase) joinPoint.proceed();
        }
        return result;
    }





//    @Around("execution(android.app.Instrumentation.ActivityResult android.app.Instrumentation.execStartActivity(..))")
//    public Instrumentation.ActivityResult onInstrumentationExecStartActivity(ProceedingJoinPoint joinPoint) throws Throwable {
//        //原函数参数：
//        Object[] args = joinPoint.getArgs();
//        Log.e(TAG, "onInstrumentationExecStartActivity:"+"参数-" + (args==null?"null":args.length));
//        //原函数执行：
//        Log.e(TAG, "before->Instrumentation");
//        Instrumentation.ActivityResult result = (Instrumentation.ActivityResult) joinPoint.proceed();
//        return result;
//    }
//    @Pointcut("call(android.database.sqlite.SQLiteDatabase android.database.sqlite.SQLiteOpenHelper.getWritableDatabase())")
//    public void callMethod() {}
//
//    @Before("callMethod()")
//    public void beforeMethodCall(JoinPoint joinPoint) {
//        Log.e(TAG, "before->DB");
//        //Log.e(TAG, "before->" + joinPoint.getTarget().toString() + "#" + joinPoint.getSignature().getName());
//    }


//    @Pointcut("call(* com.elvis.android.insert_monitor.plugin.aspectjx.Test.sss(..))")
//    public void callMethod() {}
//
//    @Before("callMethod()")
//    public void beforeMethodCall(JoinPoint joinPoint) {
//        Log.e(TAG, "before->");
//        //Log.e(TAG, "before->" + joinPoint.getTarget().toString() + "#" + joinPoint.getSignature().getName());
//    }



//    @Around("call(* com.elvis.android.insert_monitor.plugin.aspectjx.Demo.fly(..))")
//    public void aroundMethodCall(ProceedingJoinPoint joinPoint) throws Throwable {
//        Log.e(TAG, "around->" + joinPoint.getTarget().toString() + "#" + joinPoint.getSignature().getName());
//
//        // 执行原代码
//        joinPoint.proceed();
//    }
//
//    @Before("execution (* android.app.Activity.on**(..))")
//    public void onActivityMethodBefore(JoinPoint joinPoint) throws Throwable {
//        Log.e(TAG, joinPoint.getSignature().toString() + ":beforecall");
//    }


}
