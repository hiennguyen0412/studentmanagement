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
import com.example.hiennv.studentmanagement_hien.dao.SinhVienDAOImpl;
import com.example.hiennv.studentmanagement_hien.model.SinhVien;
import com.example.hiennv.studentmanagement_hien.utils.IOFile;
import com.example.hiennv.studentmanagement_hien.utils.SQLiteConnection;

public class EditActivity extends AppCompatActivity {
    private EditText edtID;
    private EditText edtName;
    private EditText edtEmail;
    private Button btnUpdate;
    private Button btncancel;
    private SinhVienDAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        init();
        SinhVien sv =(SinhVien) getIntent().getSerializableExtra("SinhVien");
        edtID.setText(sv.getId()+"");
        edtName.setText(sv.getTen());
        edtEmail.setText(sv.getEmail());

        //dao = new SinhVienDAOImpl(new SQLiteConnection(this));
        dao = new InternalSinhVienDAOImpl(new IOFile(this));
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString().trim();
                String email = edtEmail.getText().toString().trim();
                SinhVien sv =(SinhVien) getIntent().getSerializableExtra("SinhVien");
                sv.setTen(name);
                sv.setEmail(email);
                if(dao.edit(sv)){
                    Toast.makeText(EditActivity.this, "Edit OK", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(EditActivity.this, MainActivity.class);
                    startActivity(i);
                }
            }
        });

        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EditActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    private void init() {
        edtID = findViewById(R.id.edt_id);
        edtName = findViewById(R.id.edt_name_edit);
        edtEmail = findViewById(R.id.edt_email_edit);
        btnUpdate = findViewById(R.id.btn_update);
        btncancel = findViewById(R.id.btn_cancel_edit);
    }
}
