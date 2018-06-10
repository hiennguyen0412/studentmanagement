package com.example.hiennv.studentmanagement_hien.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteStatement;

import com.example.hiennv.studentmanagement_hien.model.SinhVien;
import com.example.hiennv.studentmanagement_hien.utils.SQLiteConnection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hiennv on 6/9/18.
 */

public class SinhVienDAOImpl implements SinhVienDAO {
    private SQLiteConnection connection;

    public SinhVienDAOImpl(SQLiteConnection connection) {
        this.connection = connection;
    }

    @Override
    public boolean add(SinhVien sv) throws SQLiteException {
        SQLiteDatabase connection = this.connection.getWritableDatabase();
        String sqlAdd = "INSERT INTO SinhVien VALUES(?,?,?)";
        SQLiteStatement statement = connection.compileStatement(sqlAdd);
        statement.bindString(2, sv.getTen());
        statement.bindString(3, sv.getEmail());
        statement.execute();
        return true;
    }

    @Override
    public boolean edit(SinhVien sv) throws SQLiteException {
        SQLiteDatabase connection = this.connection.getWritableDatabase();
        String sqlAdd = "UPDATE SinhVien SET name = ?, email = ? WHERE id = ?";
        SQLiteStatement statement = connection.compileStatement(sqlAdd);
        statement.bindString(1, sv.getTen());
        statement.bindString(2, sv.getEmail());
        statement.bindLong(3, sv.getId());
        statement.executeUpdateDelete();
        return true;
    }

    @Override
    public boolean del(SinhVien sv) throws SQLiteException {
        SQLiteDatabase connection = this.connection.getWritableDatabase();
        String sqlAdd = "DELETE FROM SinhVien WHERE id = ?";
        SQLiteStatement statement = connection.compileStatement(sqlAdd);
        statement.bindLong(1, sv.getId());
        statement.executeUpdateDelete();
        return true;
    }

    @Override
    public List<SinhVien> findAll() throws SQLiteException {
        List<SinhVien> listSV = new ArrayList<>();
        String sqlFindALl = "SELECT * FROM SinhVien";
        SQLiteDatabase db = this.connection.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlFindALl, null);
        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String email = cursor.getString(2);
                SinhVien sv = new SinhVien(id, name,email);
                listSV.add(sv);
            }while (cursor.moveToNext());
        }
        return listSV;
    }

    @Override
    public List<SinhVien> searchByName(String name) throws SQLiteException {
        List<SinhVien> listSV = new ArrayList<>();
        String sqlFindALl = "SELECT * FROM SinhVien WHERE name LIKE '%"+name +"%'";
        SQLiteDatabase db = this.connection.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlFindALl, null);
        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String nameSV = cursor.getString(1);
                String email = cursor.getString(2);
                SinhVien sv = new SinhVien(id, nameSV,email);
                listSV.add(sv);
            }while (cursor.moveToNext());
        }
        return listSV;
    }
}
