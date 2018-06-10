package com.example.hiennv.studentmanagement_hien.dao;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.widget.Toast;

import com.example.hiennv.studentmanagement_hien.model.SinhVien;
import com.example.hiennv.studentmanagement_hien.utils.IOFile;

import java.util.List;

/**
 * Created by hiennv on 6/9/18.
 */

public class InternalSinhVienDAOImpl implements SinhVienDAO {
    private IOFile ioFile;
    private List<SinhVien> listSV;

    public InternalSinhVienDAOImpl(IOFile ioFile) {
        this.ioFile = ioFile;
        listSV = ioFile.loadFile();
    }

    @Override
    public boolean add(SinhVien sv) throws SQLiteException {
        int ma = 0;
        if (listSV.isEmpty()) {
            ma = 0;
        } else {
            ma = listSV.get(listSV.size() - 1).getId();
        }
        sv.setId(ma + 1);
        listSV.add(sv);
        ioFile.saveFile(listSV);
        return true;
    }

    @Override
    public boolean edit(SinhVien sv) throws SQLiteException {
        for (SinhVien x : listSV) {
            if (x.getId() == sv.getId()) {
                x.setTen(sv.getTen());
                x.setEmail(sv.getEmail());
                this.ioFile.saveFile(listSV);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean del(SinhVien sv) throws SQLiteException {
//        if (listSV.contains(sv.getId())) {
//            listSV.remove(sv);
//            this.ioFile.saveFile(listSV);
//            return true;
//        }
        if(listSV.contains(sv)){
            listSV.remove(sv);
            this.ioFile.saveFile(listSV);
            return true;
        }
        return false;
    }

    @Override
    public List<SinhVien> findAll() throws SQLiteException {
        return listSV;
    }

    @Override
    public List<SinhVien> searchByName(String name) throws SQLiteException {
        return null;
    }
}
