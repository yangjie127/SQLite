package com.yang.user.mysqlitedemo1.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YangJie on 2017/4/10.
 */

public class MyDBUtils implements DBOperateMethods {
    private static MyDBUtils myDBUtils = null;
    private MyDBHelper myDBHelper;
    private List<User> mList;

    public static MyDBUtils getInstance() {
        if (myDBUtils == null) {
            synchronized (MyDBUtils.class) {
                if (myDBUtils == null) {
                    myDBUtils = new MyDBUtils();
                }
            }
        }
        return myDBUtils;
    }

    public void initDBUtils(Context context) {
        if (context != null)
            myDBHelper = new MyDBHelper(context);
        mList = new ArrayList<>();
    }

    @Override
    public boolean insert(User user) {
        SQLiteDatabase db = myDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        if (user.getId() != 0)
            values.put("_id", user.getId());
        if (!TextUtils.isEmpty(user.getUserName()))
            values.put("name", user.getUserName());
        if (!TextUtils.isEmpty(user.getPassword()))
            values.put("password", user.getPassword());
        long _id = db.insert("yang", null, values);

        db.close();

        return _id > 0;
    }

    @Override
    public boolean delete(int userId) {
        SQLiteDatabase db = myDBHelper.getWritableDatabase();
        String selection = "_id=?";
        String[] selsectArgs = new String[]{String.valueOf(userId)};
        Cursor query = myDBHelper.getReadableDatabase().query("yang", null, selection, selsectArgs, null, null, null);
        int _id = 0;
        if (query.getCount() > 0) {
            _id = db.delete("yang", selection, selsectArgs);
        }
        query.close();
        db.close();
        ;

        return _id > 0;
    }

    @Override
    public boolean update(int userId, User user) {
        SQLiteDatabase db = myDBHelper.getWritableDatabase();
        String selecttion = "_id=?";
        String[] selectArgs = new String[]{String.valueOf(userId)};

        ContentValues values = new ContentValues();
        if (!TextUtils.isEmpty(user.getUserName())) {
            values.put("name", user.getUserName());
        }
        if (!TextUtils.isEmpty(user.getPassword())) {
            values.put("password", user.getPassword());
        }
        Cursor query = myDBHelper.getReadableDatabase().query("yang", null, selecttion, selectArgs, null, null, null);
        int _id = 0;
        if (query.getCount() > 0) {
            _id = db.update("yang", values, selecttion, selectArgs  );
        }

        query.close();

        db.close();

        return _id > 0;
    }

    @Override
    public User selectById(int userId) {
        return null;
    }

    @Override
    public List<User> selectAll() {
        return null;
    }

    @Override
    public boolean release() {
        return false;
    }

    @Override
    public boolean deleteAll() {
        return false;
    }

    @Override
    public List<User> query() {

        SQLiteDatabase db = myDBHelper.getReadableDatabase();
        Cursor cursor = db.query("yang", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
//                user.setId(cursor.getLong(cursor.getColumnIndex("_id")));
                user.setUserName(cursor.getString(cursor.getColumnIndex("name")));
                user.setPassword(cursor.getString(cursor.getColumnIndex("password")));
                mList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return mList;
    }
}
