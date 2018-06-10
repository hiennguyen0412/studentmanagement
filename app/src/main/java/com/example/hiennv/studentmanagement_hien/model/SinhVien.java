package com.example.hiennv.studentmanagement_hien.model;

import java.io.Serializable;

/**
 * Created by hiennv on 6/9/18.
 */

public class SinhVien implements Serializable {
    private int id;
    private String ten;
    private String email;

    public SinhVien() {
    }

    public SinhVien(String ten, String email) {
        this.ten = ten;
        this.email = email;
    }

    public SinhVien(int id, String ten, String email) {
        this.id = id;
        this.ten = ten;
        this.email = email;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return " id=" + id +
                ", ten='" + ten + '\'' +
                ", email='" + email + '\'';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SinhVien other = (SinhVien) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (ten != null ? ten.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
