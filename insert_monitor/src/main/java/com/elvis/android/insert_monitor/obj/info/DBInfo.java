package com.elvis.android.insert_monitor.obj.info;

import com.elvis.android.insert_monitor.obj.AbsInfo;


/**
 * Created by conghongjie on 2018/6/21.
 */

public class DBInfo extends AbsInfo {


    //SQLiteOpenHelper
    public static final String SQLiteOpenHelper_getWritableDatabase                     = "getWritableDatabase()";
    public static final String SQLiteOpenHelper_getReadableDatabase                     = "getReadableDatabase()";
    //SQLiteDatabase
    public static final String SQLiteDatabase_beginTransaction                          = "beginTransaction()";
    public static final String SQLiteDatabase_beginTransactionNonExclusive              = "beginTransactionNonExclusive()";
    public static final String SQLiteDatabase_beginTransactionWithListener              = "beginTransactionWithListener(SQLiteTransactionListener)";
    public static final String SQLiteDatabase_beginTransactionWithListenerNonExclusive  = "beginTransactionWithListenerNonExclusive(SQLiteTransactionListener)";
    public static final String SQLiteDatabase_create                                    = "create(CursorFactory)";
    public static final String SQLiteDatabase_delete                                    = "delete(String,String,String[])";
    public static final String SQLiteDatabase_deleteDatabase                            = "deleteDatabase(File)";
    public static final String SQLiteDatabase_execSQL                                   = "execSQL(String)";
    public static final String SQLiteDatabase_insert                                    = "insert(String,String,ContentValues)";
    public static final String SQLiteDatabase_insertOrThrow                             = "insertOrThrow(String,String,ContentValues)";
    public static final String SQLiteDatabase_insertWithOnConflict                      = "insertWithOnConflict(String,String,ContentValues,int)";
    public static final String SQLiteDatabase_query                                     = "query(boolean,String,String[],String,String[],String,String,String,String)";
    public static final String SQLiteDatabase_queryWithFactory                          = "queryWithFactory(CursorFactory,boolean,String,String[],String,String[],String,String,String,String,CancellationSignal)";
    public static final String SQLiteDatabase_rawQuery                                  = "rawQuery(String,String[])";
    public static final String SQLiteDatabase_rawQueryWithFactory                       = "rawQueryWithFactory(CursorFactory,String,String[],String,CancellationSignal)";
    public static final String SQLiteDatabase_replace                                   = "replace(String,String,ContentValues)";
    public static final String SQLiteDatabase_replaceOrThrow                            = "replaceOrThrow(String,String,ContentValues)";
    public static final String SQLiteDatabase_update                                    = "update(String,ContentValues,String,String[])";
    public static final String SQLiteDatabase_updateWithOnConflict                      = "updateWithOnConflict(String,ContentValues,String,String[],int)";


    public long startTime;
    public long endTime;
    public String methodName;
    public String stack;
    public String sql;

    public DBInfo(long dataTime) {
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

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getStack() {
        return stack;
    }

    public void setStack(String stack) {
        this.stack = stack;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
