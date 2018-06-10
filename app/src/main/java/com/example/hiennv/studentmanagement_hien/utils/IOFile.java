package com.example.hiennv.studentmanagement_hien.utils;

import android.content.Context;

import com.example.hiennv.studentmanagement_hien.model.SinhVien;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hiennv on 6/9/18.
 */

public class IOFile {
    private ObjectOutputStream writeFile;
    private ObjectInputStream readFile;
    private Context context;
    public IOFile(Context context) {
        this.context = context;
    }

    public List<SinhVien> loadFile() {
        List<SinhVien> listSV = null;
        try {
//            File file = new File(this.filename);
//            if (!file.exists()) {
//                /**
//                 * Neu file khong ton tai thi tao file moi return new ArrayList<>()
//                 */
//                file.createNewFile();
//                return new ArrayList<>();
//            } else {
                /**
                 * Da co file thi mo luong doc file
                 */

//                readFile = new ObjectInputStream(new FileInputStream(file));
                //ap dung cho android
                FileInputStream fin = this.context.openFileInput("test.txt");

                readFile = new ObjectInputStream(fin);
                listSV = (List<SinhVien>) readFile.readObject();
                if (listSV == null) {
                    /**
                     * Neu list k co gi thi dong file return new ArrayList<>()
                     */
                    readFile.close();
                    return new ArrayList<>();
                }
                readFile.close();
                return listSV;
//            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();

        }
        return new ArrayList<>();

    }

    public boolean saveFile(List<SinhVien> listSV) {
        try {
//            writeFile = new ObjectOutputStream(new FileOutputStream(this.filename));
//            writeFile.writeObject(listSV);
//            writeFile.close();


            //ap dung cho android
            FileOutputStream fout = this.context.openFileOutput("test.txt", Context.MODE_PRIVATE);
            writeFile = new ObjectOutputStream(fout);
            writeFile.writeObject(listSV);
            writeFile.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return true;
    }

}
