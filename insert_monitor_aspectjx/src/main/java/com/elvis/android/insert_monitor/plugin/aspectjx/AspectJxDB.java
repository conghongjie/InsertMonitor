package com.elvis.android.insert_monitor.plugin.aspectjx;

import android.database.Cursor;
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


//    String[] methods = new String[]{
//            "beginTransaction",
//            "beginTransactionNonExclusive",
//            "beginTransactionWithListener",
//            "beginTransactionWithListenerNonExclusive",
//            "create",
//            "delete",
//            "deleteDatabase",
//            "execSQL",
//            "insert",
//            "insertOrThrow",
//            "insertWithOnConflict",
//            "query",
//            "queryWithFactory",
//            "rawQuery",
//            "rawQueryWithFactory",
//            "replace",
//            "replaceOrThrow",
//            "update",
//            "updateWithOnConflict"
//    };


    /**
     * SQLiteOpenHelper
     */
    @Around("call(android.database.sqlite.SQLiteDatabase android.database.sqlite.SQLiteOpenHelper.getWritableDatabase())")
    public SQLiteDatabase onSQLiteOpenHelper_getWritableDatabase(ProceedingJoinPoint joinPoint) throws Throwable {
        SQLiteDatabase result = null;
        //检测是否在主线程
        if (IOCollector.isStart() && ThreadUtils.isMainThread()){
            long start = System.currentTimeMillis();
            result = (SQLiteDatabase) joinPoint.proceed();
            long end = System.currentTimeMillis();
            DBInfo dbInfo = new DBInfo(start);
            dbInfo.startTime = start;
            dbInfo.endTime = end;
            dbInfo.methodName = DBInfo.SQLiteOpenHelper_getWritableDatabase;
            dbInfo.stack = StackUtils.getStack(Thread.currentThread());
            dbInfo.sql = "";
            IOCollector.sendInfo(dbInfo,false);
        }else {
            result = (SQLiteDatabase) joinPoint.proceed();
        }
        return result;
    }

    @Around("call(android.database.sqlite.SQLiteDatabase android.database.sqlite.SQLiteOpenHelper.getReadableDatabase())")
    public SQLiteDatabase SQLiteOpenHelper_getReadableDatabase(ProceedingJoinPoint joinPoint) throws Throwable {
        SQLiteDatabase result = null;
        //检测是否在主线程
        if (IOCollector.isStart() && ThreadUtils.isMainThread()){
            long start = System.currentTimeMillis();
            result = (SQLiteDatabase) joinPoint.proceed();
            long end = System.currentTimeMillis();
            DBInfo dbInfo = new DBInfo(start);
            dbInfo.startTime = start;
            dbInfo.endTime = end;
            dbInfo.methodName = DBInfo.SQLiteOpenHelper_getReadableDatabase;
            dbInfo.stack = StackUtils.getStack(Thread.currentThread());
            dbInfo.sql = "";
            IOCollector.sendInfo(dbInfo,false);
        }else {
            result = (SQLiteDatabase) joinPoint.proceed();
        }
        return result;
    }




    /**
     * SQLiteDatabase
     */
    @Around("call(void android.database.sqlite.SQLiteDatabase.beginTransaction())")
    public void SQLiteDatabase_beginTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        //检测是否在主线程
        if (IOCollector.isStart() && ThreadUtils.isMainThread()){
            long start = System.currentTimeMillis();
            joinPoint.proceed();
            long end = System.currentTimeMillis();
            DBInfo dbInfo = new DBInfo(start);
            dbInfo.startTime = start;
            dbInfo.endTime = end;
            dbInfo.methodName = DBInfo.SQLiteDatabase_beginTransaction;
            dbInfo.stack = StackUtils.getStack(Thread.currentThread());
            dbInfo.sql = "";
            IOCollector.sendInfo(dbInfo,false);
        }else {
            joinPoint.proceed();
        }
        return ;
    }
    @Around("call(void android.database.sqlite.SQLiteDatabase.beginTransactionNonExclusive())")
    public void SQLiteDatabase_beginTransactionNonExclusive(ProceedingJoinPoint joinPoint) throws Throwable {
        //检测是否在主线程
        if (IOCollector.isStart() && ThreadUtils.isMainThread()){
            long start = System.currentTimeMillis();
            joinPoint.proceed();
            long end = System.currentTimeMillis();
            DBInfo dbInfo = new DBInfo(start);
            dbInfo.startTime = start;
            dbInfo.endTime = end;
            dbInfo.methodName = DBInfo.SQLiteDatabase_beginTransactionNonExclusive;
            dbInfo.stack = StackUtils.getStack(Thread.currentThread());
            dbInfo.sql = "";
            IOCollector.sendInfo(dbInfo,false);
        }else {
            joinPoint.proceed();
        }
        return ;
    }
    @Around("call(void android.database.sqlite.SQLiteDatabase.beginTransactionWithListener(android.database.sqlite.SQLiteTransactionListener))")
    public void SQLiteDatabase_beginTransactionWithListener(ProceedingJoinPoint joinPoint) throws Throwable {
        //检测是否在主线程
        if (IOCollector.isStart() && ThreadUtils.isMainThread()){
            long start = System.currentTimeMillis();
            joinPoint.proceed();
            long end = System.currentTimeMillis();
            DBInfo dbInfo = new DBInfo(start);
            dbInfo.startTime = start;
            dbInfo.endTime = end;
            dbInfo.methodName = DBInfo.SQLiteDatabase_beginTransactionWithListener;
            dbInfo.stack = StackUtils.getStack(Thread.currentThread());
            dbInfo.sql = "";
            IOCollector.sendInfo(dbInfo,false);
        }else {
            joinPoint.proceed();
        }
        return ;
    }
    @Around("call(void android.database.sqlite.SQLiteDatabase.beginTransactionWithListenerNonExclusive(android.database.sqlite.SQLiteTransactionListener))")
    public void SQLiteDatabase_beginTransactionWithListenerNonExclusive(ProceedingJoinPoint joinPoint) throws Throwable {
        //检测是否在主线程
        if (IOCollector.isStart() && ThreadUtils.isMainThread()){
            long start = System.currentTimeMillis();
            joinPoint.proceed();
            long end = System.currentTimeMillis();
            DBInfo dbInfo = new DBInfo(start);
            dbInfo.startTime = start;
            dbInfo.endTime = end;
            dbInfo.methodName = DBInfo.SQLiteDatabase_beginTransactionWithListenerNonExclusive;
            dbInfo.stack = StackUtils.getStack(Thread.currentThread());
            dbInfo.sql = "";
            IOCollector.sendInfo(dbInfo,false);
        }else {
            joinPoint.proceed();
        }
        return ;
    }

    @Around("call(android.database.sqlite.SQLiteDatabase android.database.sqlite.SQLiteDatabase.create(android.database.sqlite.SQLiteDatabase.CursorFactory))")
    public SQLiteDatabase SQLiteDatabase_create(ProceedingJoinPoint joinPoint) throws Throwable {
        SQLiteDatabase result;
        //检测是否在主线程
        if (IOCollector.isStart() && ThreadUtils.isMainThread()){
            long start = System.currentTimeMillis();
            result = (SQLiteDatabase) joinPoint.proceed();
            long end = System.currentTimeMillis();
            DBInfo dbInfo = new DBInfo(start);
            dbInfo.startTime = start;
            dbInfo.endTime = end;
            dbInfo.methodName = DBInfo.SQLiteDatabase_create;
            dbInfo.stack = StackUtils.getStack(Thread.currentThread());
            dbInfo.sql = "";
            IOCollector.sendInfo(dbInfo,false);
        }else {
            result = (SQLiteDatabase) joinPoint.proceed();
        }
        return result;
    }


    @Around("call(int android.database.sqlite.SQLiteDatabase.delete(java.lang.String,java.lang.String,java.lang.String[]))")
    public int SQLiteDatabase_delete(ProceedingJoinPoint joinPoint) throws Throwable {
        int result;
        //检测是否在主线程
        if (IOCollector.isStart() && ThreadUtils.isMainThread()){
            long start = System.currentTimeMillis();
            result = (int) joinPoint.proceed();
            long end = System.currentTimeMillis();
            DBInfo dbInfo = new DBInfo(start);
            dbInfo.startTime = start;
            dbInfo.endTime = end;
            dbInfo.methodName = DBInfo.SQLiteDatabase_delete;
            dbInfo.stack = StackUtils.getStack(Thread.currentThread());
            dbInfo.sql = "";
            IOCollector.sendInfo(dbInfo,false);
        }else {
            result = (int) joinPoint.proceed();
        }
        return result;
    }

    @Around("call(boolean android.database.sqlite.SQLiteDatabase.deleteDatabase(java.io.File))")
    public boolean SQLiteDatabase_deleteDatabase(ProceedingJoinPoint joinPoint) throws Throwable {
        boolean result;
        //检测是否在主线程
        if (IOCollector.isStart() && ThreadUtils.isMainThread()){
            long start = System.currentTimeMillis();
            result = (boolean) joinPoint.proceed();
            long end = System.currentTimeMillis();
            DBInfo dbInfo = new DBInfo(start);
            dbInfo.startTime = start;
            dbInfo.endTime = end;
            dbInfo.methodName = DBInfo.SQLiteDatabase_deleteDatabase;
            dbInfo.stack = StackUtils.getStack(Thread.currentThread());
            dbInfo.sql = "";
            IOCollector.sendInfo(dbInfo,false);
        }else {
            result = (boolean) joinPoint.proceed();
        }
        return result;
    }

    @Around("call(void android.database.sqlite.SQLiteDatabase.execSQL(java.lang.String))")
    public void SQLiteDatabase_execSQL(ProceedingJoinPoint joinPoint) throws Throwable {
        //检测是否在主线程
        if (IOCollector.isStart() && ThreadUtils.isMainThread()){
            long start = System.currentTimeMillis();
            joinPoint.proceed();
            long end = System.currentTimeMillis();
            DBInfo dbInfo = new DBInfo(start);
            dbInfo.startTime = start;
            dbInfo.endTime = end;
            dbInfo.methodName = DBInfo.SQLiteDatabase_execSQL;
            dbInfo.stack = StackUtils.getStack(Thread.currentThread());
            dbInfo.sql = "";
            IOCollector.sendInfo(dbInfo,false);
        }else {
            joinPoint.proceed();
        }
        return ;
    }

    @Around("call(long android.database.sqlite.SQLiteDatabase.insert(java.lang.String,java.lang.String,android.content.ContentValues))")
    public long SQLiteDatabase_insert(ProceedingJoinPoint joinPoint) throws Throwable {
        long result;
        //检测是否在主线程
        if (IOCollector.isStart() && ThreadUtils.isMainThread()){
            long start = System.currentTimeMillis();
            result = (long) joinPoint.proceed();
            long end = System.currentTimeMillis();
            DBInfo dbInfo = new DBInfo(start);
            dbInfo.startTime = start;
            dbInfo.endTime = end;
            dbInfo.methodName = DBInfo.SQLiteDatabase_insert;
            dbInfo.stack = StackUtils.getStack(Thread.currentThread());
            dbInfo.sql = "";
            IOCollector.sendInfo(dbInfo,false);
        }else {
            result = (long) joinPoint.proceed();
        }
        return result;
    }

    @Around("call(long android.database.sqlite.SQLiteDatabase.insertOrThrow(java.lang.String,java.lang.String,android.content.ContentValues))")
    public long SQLiteDatabase_insertOrThrow(ProceedingJoinPoint joinPoint) throws Throwable {
        long result;
        //检测是否在主线程
        if (IOCollector.isStart() && ThreadUtils.isMainThread()){
            long start = System.currentTimeMillis();
            result = (long) joinPoint.proceed();
            long end = System.currentTimeMillis();
            DBInfo dbInfo = new DBInfo(start);
            dbInfo.startTime = start;
            dbInfo.endTime = end;
            dbInfo.methodName = DBInfo.SQLiteDatabase_insertOrThrow;
            dbInfo.stack = StackUtils.getStack(Thread.currentThread());
            dbInfo.sql = "";
            IOCollector.sendInfo(dbInfo,false);
        }else {
            result = (long) joinPoint.proceed();
        }
        return result;
    }

    @Around("call(long android.database.sqlite.SQLiteDatabase.insertWithOnConflict(java.lang.String,java.lang.String,android.content.ContentValues,int))")
    public long SQLiteDatabase_insertWithOnConflict(ProceedingJoinPoint joinPoint) throws Throwable {
        long result;
        //检测是否在主线程
        if (IOCollector.isStart() && ThreadUtils.isMainThread()){
            long start = System.currentTimeMillis();
            result = (long) joinPoint.proceed();
            long end = System.currentTimeMillis();
            DBInfo dbInfo = new DBInfo(start);
            dbInfo.startTime = start;
            dbInfo.endTime = end;
            dbInfo.methodName = DBInfo.SQLiteDatabase_insertWithOnConflict;
            dbInfo.stack = StackUtils.getStack(Thread.currentThread());
            dbInfo.sql = "";
            IOCollector.sendInfo(dbInfo,false);
        }else {
            result = (long) joinPoint.proceed();
        }
        return result;
    }


    @Around("call(android.database.Cursor android.database.sqlite.SQLiteDatabase.query(boolean,java.lang.String,java.lang.String[],java.lang.String,java.lang.String[],java.lang.String,java.lang.String,java.lang.String,java.lang.String))")
    public Cursor SQLiteDatabase_query(ProceedingJoinPoint joinPoint) throws Throwable {
        Cursor result;
        //检测是否在主线程
        if (IOCollector.isStart() && ThreadUtils.isMainThread()){
            long start = System.currentTimeMillis();
            result = (Cursor) joinPoint.proceed();
            long end = System.currentTimeMillis();
            DBInfo dbInfo = new DBInfo(start);
            dbInfo.startTime = start;
            dbInfo.endTime = end;
            dbInfo.methodName = DBInfo.SQLiteDatabase_query;
            dbInfo.stack = StackUtils.getStack(Thread.currentThread());
            dbInfo.sql = "";
            IOCollector.sendInfo(dbInfo,false);
        }else {
            result = (Cursor) joinPoint.proceed();
        }
        return result;
    }

    @Around("call(android.database.Cursor android.database.sqlite.SQLiteDatabase.queryWithFactory(android.database.sqlite.SQLiteDatabase.CursorFactory,boolean,java.lang.String,java.lang.String[],java.lang.String,java.lang.String[],java.lang.String,java.lang.String,java.lang.String,java.lang.String,android.os.CancellationSignal))")
    public Cursor SQLiteDatabase_queryWithFactory(ProceedingJoinPoint joinPoint) throws Throwable {
        Cursor result;
        //检测是否在主线程
        if (IOCollector.isStart() && ThreadUtils.isMainThread()){
            long start = System.currentTimeMillis();
            result = (Cursor) joinPoint.proceed();
            long end = System.currentTimeMillis();
            DBInfo dbInfo = new DBInfo(start);
            dbInfo.startTime = start;
            dbInfo.endTime = end;
            dbInfo.methodName = DBInfo.SQLiteDatabase_queryWithFactory;
            dbInfo.stack = StackUtils.getStack(Thread.currentThread());
            dbInfo.sql = "";
            IOCollector.sendInfo(dbInfo,false);
        }else {
            result = (Cursor) joinPoint.proceed();
        }
        return result;
    }

    @Around("call(android.database.Cursor android.database.sqlite.SQLiteDatabase.rawQuery(java.lang.String,java.lang.String[]))")
    public Cursor SQLiteDatabase_rawQuery(ProceedingJoinPoint joinPoint) throws Throwable {
        Cursor result;
        //检测是否在主线程
        if (IOCollector.isStart() && ThreadUtils.isMainThread()){
            long start = System.currentTimeMillis();
            result = (Cursor) joinPoint.proceed();
            long end = System.currentTimeMillis();
            DBInfo dbInfo = new DBInfo(start);
            dbInfo.startTime = start;
            dbInfo.endTime = end;
            dbInfo.methodName = DBInfo.SQLiteDatabase_rawQuery;
            dbInfo.stack = StackUtils.getStack(Thread.currentThread());
            dbInfo.sql = "";
            IOCollector.sendInfo(dbInfo,false);
        }else {
            result = (Cursor) joinPoint.proceed();
        }
        return result;
    }

    @Around("call(android.database.Cursor android.database.sqlite.SQLiteDatabase.rawQueryWithFactory(android.database.sqlite.SQLiteDatabase.CursorFactory,java.lang.String,java.lang.String[],java.lang.String,android.os.CancellationSignal))")
    public Cursor SQLiteDatabase_rawQueryWithFactory(ProceedingJoinPoint joinPoint) throws Throwable {
        Cursor result;
        //检测是否在主线程
        if (IOCollector.isStart() && ThreadUtils.isMainThread()){
            long start = System.currentTimeMillis();
            result = (Cursor) joinPoint.proceed();
            long end = System.currentTimeMillis();
            DBInfo dbInfo = new DBInfo(start);
            dbInfo.startTime = start;
            dbInfo.endTime = end;
            dbInfo.methodName = DBInfo.SQLiteDatabase_rawQueryWithFactory;
            dbInfo.stack = StackUtils.getStack(Thread.currentThread());
            dbInfo.sql = "";
            IOCollector.sendInfo(dbInfo,false);
        }else {
            result = (Cursor) joinPoint.proceed();
        }
        return result;
    }



    @Around("call(long android.database.sqlite.SQLiteDatabase.replace(java.lang.String,java.lang.String,android.content.ContentValues))")
    public long SQLiteDatabase_replace(ProceedingJoinPoint joinPoint) throws Throwable {
        long result;
        //检测是否在主线程
        if (IOCollector.isStart() && ThreadUtils.isMainThread()){
            long start = System.currentTimeMillis();
            result = (long) joinPoint.proceed();
            long end = System.currentTimeMillis();
            DBInfo dbInfo = new DBInfo(start);
            dbInfo.startTime = start;
            dbInfo.endTime = end;
            dbInfo.methodName = DBInfo.SQLiteDatabase_replace;
            dbInfo.stack = StackUtils.getStack(Thread.currentThread());
            dbInfo.sql = "";
            IOCollector.sendInfo(dbInfo,false);
        }else {
            result = (long) joinPoint.proceed();
        }
        return result;
    }

    @Around("call(long android.database.sqlite.SQLiteDatabase.replaceOrThrow(java.lang.String,java.lang.String,android.content.ContentValues))")
    public long SQLiteDatabase_replaceOrThrow(ProceedingJoinPoint joinPoint) throws Throwable {
        long result;
        //检测是否在主线程
        if (IOCollector.isStart() && ThreadUtils.isMainThread()){
            long start = System.currentTimeMillis();
            result = (long) joinPoint.proceed();
            long end = System.currentTimeMillis();
            DBInfo dbInfo = new DBInfo(start);
            dbInfo.startTime = start;
            dbInfo.endTime = end;
            dbInfo.methodName = DBInfo.SQLiteDatabase_replaceOrThrow;
            dbInfo.stack = StackUtils.getStack(Thread.currentThread());
            dbInfo.sql = "";
            IOCollector.sendInfo(dbInfo,false);
        }else {
            result = (long) joinPoint.proceed();
        }
        return result;
    }




    @Around("call(int android.database.sqlite.SQLiteDatabase.update(java.lang.String,android.content.ContentValues,java.lang.String,java.lang.String[]))")
    public int SQLiteDatabase_update(ProceedingJoinPoint joinPoint) throws Throwable {
        int result;
        //检测是否在主线程
        if (IOCollector.isStart() && ThreadUtils.isMainThread()){
            long start = System.currentTimeMillis();
            result = (int) joinPoint.proceed();
            long end = System.currentTimeMillis();
            DBInfo dbInfo = new DBInfo(start);
            dbInfo.startTime = start;
            dbInfo.endTime = end;
            dbInfo.methodName = DBInfo.SQLiteDatabase_update;
            dbInfo.stack = StackUtils.getStack(Thread.currentThread());
            dbInfo.sql = "";
            IOCollector.sendInfo(dbInfo,false);
        }else {
            result = (int) joinPoint.proceed();
        }
        return result;
    }

    @Around("call(int android.database.sqlite.SQLiteDatabase.updateWithOnConflict(java.lang.String,android.content.ContentValues,java.lang.String,java.lang.String[],int))")
    public int SQLiteDatabase_updateWithOnConflict(ProceedingJoinPoint joinPoint) throws Throwable {
        int result;
        //检测是否在主线程
        if (IOCollector.isStart() && ThreadUtils.isMainThread()){
            long start = System.currentTimeMillis();
            result = (int) joinPoint.proceed();
            long end = System.currentTimeMillis();
            DBInfo dbInfo = new DBInfo(start);
            dbInfo.startTime = start;
            dbInfo.endTime = end;
            dbInfo.methodName = DBInfo.SQLiteDatabase_updateWithOnConflict;
            dbInfo.stack = StackUtils.getStack(Thread.currentThread());
            dbInfo.sql = "";
            IOCollector.sendInfo(dbInfo,false);
        }else {
            result = (int) joinPoint.proceed();
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
