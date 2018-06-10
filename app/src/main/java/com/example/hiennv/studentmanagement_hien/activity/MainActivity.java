package com.example.hiennv.studentmanagement_hien.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hiennv.studentmanagement_hien.R;
import com.example.hiennv.studentmanagement_hien.adapter.SinhVienAdapter;
import com.example.hiennv.studentmanagement_hien.dao.InternalSinhVienDAOImpl;
import com.example.hiennv.studentmanagement_hien.dao.SinhVienDAO;
import com.example.hiennv.studentmanagement_hien.dao.SinhVienDAOImpl;
import com.example.hiennv.studentmanagement_hien.model.SinhVien;
import com.example.hiennv.studentmanagement_hien.utils.IOFile;
import com.example.hiennv.studentmanagement_hien.utils.SQLiteConnection;

import java.nio.BufferUnderflowException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btnAdd;
    private Button btnSearch;
    private EditText edtSearch;
    private ListView listViewSV;
    private SinhVienDAO dao;
    private List<SinhVien> listSV;
    private SinhVienAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        listSV = new ArrayList<>();
        dao = new InternalSinhVienDAOImpl(new IOFile(MainActivity.this));
        listSV = dao.findAll();
        //dao = new SinhVienDAOImpl(new SQLiteConnection(this));
        SinhVien sv1 = new SinhVien(1,"hien", "hien@gmail.com");
        SinhVien sv2 = new SinhVien(2,"chi", "chi@gmail.com");
        SinhVien sv3 = new SinhVien(3,"yen", "yen@gmail.com");
//        listSV.add(sv1);
//        listSV.add(sv2);
//        listSV.add(sv3);





        adapter = new SinhVienAdapter(this,R.layout.row_item_sv, listSV);
        listViewSV.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtSearch.getText().toString().trim();
                List<SinhVien> list = new ArrayList<>();
                list = dao.searchByName(name);
                adapter = new SinhVienAdapter(MainActivity.this, R.layout.row_item_sv, list);
                listViewSV.setAdapter(adapter);
                //adapter.notifyDataSetChanged();
            }
        });

    }

    private void init() {
        btnAdd = this.findViewById(R.id.btn_add);
        btnSearch = this.findViewById(R.id.btn_search);
        edtSearch = this.findViewById(R.id.edt_search);
        listViewSV = this.findViewById(R.id.list_view_sv);

    }
}
