package com.example.appstudent.Model;

import android.widget.TextView;

import com.example.appstudent.R;

import java.io.Serializable;

public class SinhVien extends Person implements Serializable {
    private String Lop;
    private String chuyennganh,khoa,hedaotao,NgaySinh,Dienthoai,Email;
    private int GioiTinh;
    public SinhVien() {
    }
    public SinhVien(String hoTenSinhVien, String maSinhVien, String lop) {
        super(hoTenSinhVien,maSinhVien);
        Lop = lop;
    }

    public SinhVien(String hoTen, String ma, String lop, String ngaySinh, String dienthoai, String email, int gioiTinh) {
        super(hoTen, ma);
        Lop = lop;
        NgaySinh = ngaySinh;
        Dienthoai = dienthoai;
        Email = email;
        GioiTinh = gioiTinh;
    }

    public SinhVien(String hoTen, String ma, String lop, String chuyennganh, String khoa, String hedaotao, String ngaySinh, String dienthoai, String email, int gioiTinh) {
        super(hoTen, ma);
        Lop = lop;
        this.chuyennganh = chuyennganh;
        this.khoa = khoa;
        this.hedaotao = hedaotao;
        NgaySinh = ngaySinh;
        Dienthoai = dienthoai;
        Email = email;
        GioiTinh = (gioiTinh == 1? R.drawable.namgiaumat:R.drawable.nugiaumat);
    }

    public int getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(int gioiTinh) {
        GioiTinh = gioiTinh;
    }

    public String getChuyennganh() {
        return chuyennganh;
    }

    public void setChuyennganh(String chuyennganh) {
        this.chuyennganh = chuyennganh;
    }

    public String getKhoa() {
        return khoa;
    }

    public void setKhoa(String khoa) {
        this.khoa = khoa;
    }

    public String getHedaotao() {
        return hedaotao;
    }

    public void setHedaotao(String hedaotao) {
        this.hedaotao = hedaotao;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        NgaySinh = ngaySinh;
    }


    public String getDienthoai() {
        return Dienthoai;
    }

    public void setDienthoai(String dienthoai) {
        Dienthoai = dienthoai;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getLop() {
        return Lop;
    }

    public void setLop(String lop) {
        Lop = lop;
    }
}
