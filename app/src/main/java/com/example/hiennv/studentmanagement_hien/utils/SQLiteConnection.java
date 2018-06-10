package com.example.hiennv.studentmanagement_hien.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hiennv on 6/9/18.
 */

public class SQLiteConnection extends SQLiteOpenHelper {
    private Context context;
    private static final String dbName = "sinhvien";
    public SQLiteConnection(Context context) {
        super(context, dbName, null,1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreate = "CREATE TABLE SinhVien(id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                "name TEXT," +
                "email TEXT);";
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sqlDel = "DROP TABLE IF EXIST"+dbName;
        onCreate(db);
    }
}
