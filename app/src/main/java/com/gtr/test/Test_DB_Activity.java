package com.gtr.test;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;

import com.elvis.android.lib.insert_monitor.R;

import java.util.ArrayList;

public class Test_DB_Activity extends Activity {

    private static final String TAG = "Test_DB";

    MySQLiteHelper mySQLiteHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_db);


        mySQLiteHelper = new MySQLiteHelper(this.getApplicationContext());


        //增查：
        Log.e(TAG,"数据库数据:"+"1");
        mySQLiteHelper.insertData("1","elvis");
        Log.e(TAG,"数据库数据:"+"2");
        mySQLiteHelper.insertData("2","sven");
        Log.e(TAG,"数据库数据:"+"3");
        mySQLiteHelper.insertData("3","david");
        Log.e(TAG,"数据库数据:"+"4");
        mySQLiteHelper.insertData("4","david");
        Log.e(TAG,"数据库数据:"+"5");
        mySQLiteHelper.insertData("5","david");

        //删查：
        mySQLiteHelper.deleteData("2","sven");

        //改查：
        mySQLiteHelper.updateData("1","eeeee");



        new Thread(new Runnable() {
            @Override
            public void run() {
                //增查：
                Log.e(TAG,"数据库数据:"+"6");
                mySQLiteHelper.insertData("6","elvis");
            }
        }).start();


    }


    public class MySQLiteHelper extends SQLiteOpenHelper {

        public static final String DBName = "hero_info";

        //调用父类构造器
        public MySQLiteHelper(Context context) {
            super(context, DBName, null, 1);
        }

        /**
         * 当数据库首次创建时执行该方法，一般将创建表等初始化操作放在该方法中执行.
         * 重写onCreate方法，调用execSQL方法创建表
         * */
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table if not exists "+DBName+"("
                    + "id integer primary key,"
                    + "name varchar)");

        }

        //当打开数据库时传入的版本号与当前的版本号不同时会调用该方法
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }


        /**
         * 增
         */
        public void insertData(String id, String name) {
            try{
                //设置属性：
                ContentValues dataValue = new ContentValues();
                dataValue.put("id", id);
                dataValue.put("name", name);
                //插入数据库：
                SQLiteDatabase databaseWrite = this.getWritableDatabase();
                databaseWrite.insert(DBName, null, dataValue);
                databaseWrite.close();
            }catch (Throwable e){
                e.printStackTrace();
            }
        }

        /**
         * 删
         */
        public void deleteData(String id, String name) {
            SQLiteDatabase databaseWrite = this.getWritableDatabase();
            databaseWrite.delete(DBName, "id = ? and name = ?", new String[]{id, name});
            databaseWrite.close();
        }


        void updateData(String id , String name){
            SQLiteDatabase databaseWrite = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("name", name);//key为字段名，value为值
            databaseWrite.update(DBName, values, "id=?", new String[]{id});
            databaseWrite.close();
        }


        /**
         * 查
         */
        public ArrayList<String> quaryNameList() {
            ArrayList<String> nameList = new ArrayList<String>();
            SQLiteDatabase database = this.getReadableDatabase();
            Cursor cursor = database.query(DBName, null, null, null, null, null, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    nameList.add(name);
                }
            }
            return nameList;
        }



    }





}
