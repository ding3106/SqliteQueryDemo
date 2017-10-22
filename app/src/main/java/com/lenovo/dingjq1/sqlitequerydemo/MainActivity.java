package com.lenovo.dingjq1.sqlitequerydemo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.lenovo.dingjq1.sqlitequerydemo.com.imooc.bean.Person;
import com.lenovo.dingjq1.sqlitequerydemo.utils.Constants;
import com.lenovo.dingjq1.sqlitequerydemo.utils.DbManager;
import com.lenovo.dingjq1.sqlitequerydemo.utils.MySqliteHelper;

import java.util.List;

/**
 *
 */
/*
*演示Sqlite中的查询数据库操作
* */
public class MainActivity extends AppCompatActivity {

    private MySqliteHelper helper;

    private static final String TAG = "dingjq1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate() called with: savedInstanceState = [" + savedInstanceState + "]");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = DbManager.getInstance(this);
    }

    /**
     * @param view
     */
    public void createDB(View view) {

        SQLiteDatabase db = helper.getWritableDatabase();
        for (int i = 1; i <= 100; i++) {
            String sql = "insert into " + Constants.Table_name + " values(" + i + ",'张三" + i + "', 20)";
            db.execSQL(sql);
        }
        db.close();
    }

    /**
     * @param view
     */
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sql_query:
                SQLiteDatabase db = helper.getWritableDatabase();
                String sql = "select * from " + Constants.Table_name;
                Cursor cursor = DbManager.selectDataBySql(db, sql, null);
                List<Person> list = DbManager.cursorToList(cursor);
                for (Person person : list) {
                    Log.i(TAG, "onClick: persion: " + person.toString());

                }
                db.close();
                break;
            case R.id.btn_api:
                db = helper.getWritableDatabase();
                cursor = db.query(Constants.Table_name, null, Constants._ID + ">?",
                        new String[]{"10"}, null, null, Constants._ID + " desc");
                list = DbManager.cursorToList(cursor);
                for (Person p : list) {
                    Log.i(TAG, "onClick: person: " + p.toString());
                }
                db.close();
                break;
            case R.id.btn_startActivity:
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
                break;


        }
    }
}
