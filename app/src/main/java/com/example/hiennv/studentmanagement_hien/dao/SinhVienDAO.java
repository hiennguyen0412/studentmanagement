package com.example.hiennv.studentmanagement_hien.dao;

import android.database.sqlite.SQLiteException;

import com.example.hiennv.studentmanagement_hien.model.SinhVien;

import java.util.List;

/**
 * Created by hiennv on 6/9/18.
 */

public interface SinhVienDAO {
    boolean add(SinhVien sv) throws SQLiteException;
    boolean edit(SinhVien sv) throws SQLiteException;
    boolean del(SinhVien sv) throws SQLiteException;
    List<SinhVien> findAll() throws SQLiteException;
    List<SinhVien> searchByName(String name) throws SQLiteException;
}
