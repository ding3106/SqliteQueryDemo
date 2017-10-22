package com.lenovo.dingjq1.sqlitequerydemo.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lenovo.dingjq1.sqlitequerydemo.com.imooc.bean.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dingjq on 2017/10/9.
 */

public class DbManager {
    private static MySqliteHelper helper;

    public static MySqliteHelper getInstance(Context context) {
        if (helper == null) {
            helper = new MySqliteHelper(context);
        }
        return helper;
    }

    /*
    * 根据sql语句查询获得cursor对象
    *
    * */

    public static Cursor selectDataBySql(SQLiteDatabase db, String sql, String[] selectionArgs) {
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(sql, selectionArgs);
        }
        return cursor;
    }


    /**
     * @param cursor
     * @return
     */

    public static List<Person> cursorToList(Cursor cursor) {
        List<Person> list = new ArrayList<>();
        //
        while (cursor.moveToNext()) {
            int columnIndex = cursor.getColumnIndex(Constants._ID);
            int _id = cursor.getInt(columnIndex);
            String name = cursor.getString(cursor.getColumnIndex(Constants.NAME));
            int age = cursor.getInt(cursor.getColumnIndex(Constants.AGE));
            Person person = new Person(_id, name, age);
            list.add(person);

        }
        return list;
    }

    public static int getCountNum(SQLiteDatabase db, String tableName) {
        int count = 0;
        if (db != null) {
            Cursor cursor = db.rawQuery("select * from " + tableName, null);
            count = cursor.getCount();
        }
        return count;
    }

    /**
     * 根据当前页码获取该页码对应的集合数据
     *
     * @param db
     * @param tableName
     * @param currentPage
     * @param pageSize
     * @return select * from person ?,?   如何根据当前页码获取当页数据
     * 0,20    1
     * 20,20   2
     * 40,20   3
     */
    public static List<Person> getListByCurrentPage(SQLiteDatabase db,
                                                    String tableName, int currentPage, int pageSize) {
        int index = (currentPage - 1) * pageSize;//获取当前页码第一条数据的下标
        Cursor cursor = null;
        if (db != null) {
            String sql = "select * from " + tableName + " limit ?,?";
            cursor = db.rawQuery(sql, new String[]{index + "", pageSize + ""});

        }
        return cursorToList(cursor);

    }

}
