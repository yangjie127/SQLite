package com.yang.user.mysqlitedemo1.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by YangJie on 2017/4/10.
 */

public class MyDBHelper extends SQLiteOpenHelper{
    private static final String DB_NAME = "yangjie.db"; //数据库名称
    private static final int version = 1; //数据库版本
    private static final String  TABLE_NAME = "yangjie";
    public MyDBHelper(Context context){
        super(context,DB_NAME,null,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table " + TABLE_NAME + " (_id integer primary key autoincrement,name varchar(20) not null,password varchar(60) not null );";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion > oldVersion ){
            db.execSQL("DROP TABLE IF EXTSTS " + TABLE_NAME);
            onCreate(db);
        }
    }
}
