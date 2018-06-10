package com.example.hiennv.studentmanagement_hien.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hiennv.studentmanagement_hien.R;
import com.example.hiennv.studentmanagement_hien.dao.InternalSinhVienDAOImpl;
import com.example.hiennv.studentmanagement_hien.dao.SinhVienDAO;
import com.example.hiennv.studentmanagement_hien.model.SinhVien;
import com.example.hiennv.studentmanagement_hien.utils.IOFile;
import com.example.hiennv.studentmanagement_hien.utils.SQLiteConnection;

public class AddActivity extends AppCompatActivity {
    private EditText edtName;
    private EditText edtEmail;
    private Button btnAdd;
    private Button btncancel;
    private SinhVienDAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        init();
        //dao = new SinhVienDAOImpl(new SQLiteConnection(this));
        dao = new InternalSinhVienDAOImpl(new IOFile(AddActivity.this));

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString().trim();
                String email = edtEmail.getText().toString().trim();
                SinhVien sv = new SinhVien(name, email);
                if(dao.add(sv)){
                    Toast.makeText(AddActivity.this, "Add OK", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(AddActivity.this, MainActivity.class);
                    startActivity(i);
                }
            }
        });

        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    private void init() {
        edtName = findViewById(R.id.edt_name);
        edtEmail = findViewById(R.id.edt_email);
        btnAdd = findViewById(R.id.btn_add_add);
        btncancel = findViewById(R.id.btn_cancel_add);
    }
}
