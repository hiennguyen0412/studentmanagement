package com.example.hiennv.studentmanagement_hien.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hiennv.studentmanagement_hien.R;
import com.example.hiennv.studentmanagement_hien.activity.EditActivity;
import com.example.hiennv.studentmanagement_hien.dao.InternalSinhVienDAOImpl;
import com.example.hiennv.studentmanagement_hien.dao.SinhVienDAO;
import com.example.hiennv.studentmanagement_hien.dao.SinhVienDAOImpl;
import com.example.hiennv.studentmanagement_hien.model.SinhVien;
import com.example.hiennv.studentmanagement_hien.utils.IOFile;
import com.example.hiennv.studentmanagement_hien.utils.SQLiteConnection;

import java.util.List;

/**
 * Created by hiennv on 6/9/18.
 */

public class SinhVienAdapter extends ArrayAdapter<SinhVien> {
    private Context context;
    private List<SinhVien> listSV;
    private int resource;
    private SinhVienDAO dao;
    public SinhVienAdapter(@NonNull Context context,int resource, @NonNull List<SinhVien> objects) {
        super(context, resource,objects);
        this.context = context;
        this.listSV = objects;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(this.context).inflate(R.layout.row_item_sv, parent, false);
        TextView tvSV = convertView.findViewById(R.id.tv_sv);
        Button btnEdit = convertView.findViewById(R.id.btn_edit);
        Button btnDel = convertView.findViewById(R.id.btn_del);

        SinhVien sv = listSV.get(position);
        tvSV.setText(sv.toString());

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SinhVien sv = listSV.get(position);
                Intent i = new Intent(context, EditActivity.class);
                i.putExtra("SinhVien", sv);
                context.startActivity(i);
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder;
                builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete student")
                        .setMessage("Are you sure you want to delete this student?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                                SinhVien sv = listSV.get(position);
                                //dao = new SinhVienDAOImpl(new SQLiteConnection(context));
                                dao = new InternalSinhVienDAOImpl(new IOFile(context));
                                Log.i("chay den day", "OK");
                                if(dao.del(sv)){
                                    Toast.makeText(context, "DEL OK", Toast.LENGTH_SHORT).show();
                                    listSV.remove(sv);
                                    SinhVienAdapter.this.notifyDataSetChanged();
                                }
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });

        return convertView;
    }
}
