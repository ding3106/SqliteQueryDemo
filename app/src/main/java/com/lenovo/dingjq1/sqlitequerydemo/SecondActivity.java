package com.lenovo.dingjq1.sqlitequerydemo;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ListView;

import com.lenovo.dingjq1.sqlitequerydemo.adapter.MyBaseAdapter;
import com.lenovo.dingjq1.sqlitequerydemo.com.imooc.bean.Person;
import com.lenovo.dingjq1.sqlitequerydemo.utils.Constants;
import com.lenovo.dingjq1.sqlitequerydemo.utils.DbManager;
import com.lenovo.dingjq1.sqlitequerydemo.utils.MySqliteHelper;

import java.io.File;
import java.util.List;

/**
 * 演示数据库分页
 * Created by dingjq on 2017/10/19.
 */

public class SecondActivity extends Activity {

    private ListView lv;
    MySqliteHelper sqliteHelper;
    private SQLiteDatabase db;
    private static final String TAG = "dingjq2";
    private int totalNum;
    private int pageSize = 15;
    private int pageNum;
    private int currentPage = 1;
    private List<Person> totalList;
    MyBaseAdapter adapter;
    private boolean isDivPage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate() called with: savedInstanceState = [" + savedInstanceState + "]");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_second);
        lv = findViewById(R.id.lv);
        sqliteHelper = DbManager.getInstance(this);

        //1.数据库查询得到的数据源
        String path = "/data/data/com.lenovo.dingjq1.sqlitequerydemo/databases/info.db";
        Log.d(TAG, "onCreate: path = " + path);
        File dbFile = this.getDatabasePath(path);
        Log.d(TAG, "onCreate: idDbFile_exist:" + dbFile.exists());
        //SQLiteDatabase db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);

        db = sqliteHelper.getWritableDatabase();
        final Cursor cursor = db.rawQuery("select * from " + Constants.Table_name, null);
        //获取数据表总条目数
        totalNum = DbManager.getCountNum(db, Constants.Table_name);
        //根据总条目数 和 每页要展示的条目数  获取总页数
        pageNum = (int) Math.ceil(totalNum / (double) pageSize);
        if (currentPage == 1) {
            totalList = DbManager.getListByCurrentPage(db, Constants.Table_name, currentPage, pageSize);
        }

        adapter = new MyBaseAdapter(this, totalList);
        lv.setAdapter(adapter);

        lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (isDivPage && AbsListView.OnScrollListener.SCROLL_STATE_IDLE == scrollState) {
                    if (currentPage < pageNum) {
                        currentPage++;
                        totalList.addAll(DbManager.getListByCurrentPage(db, Constants.Table_name, currentPage, pageSize));
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                isDivPage = ((firstVisibleItem + visibleItemCount) == totalItemCount);
            }
        });
/*
        // 2. 将数据源数据加载到适配器中
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.list_item, cursor,
                new String[]{Constants._ID, Constants.NAME, Constants.AGE},
                new int[]{R.id.tv_id, R.id.tv_name, R.id.tv_age},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        //3. 将适配器的数据加载到控件
        lv.setAdapter(adapter);
*/

    }


}
