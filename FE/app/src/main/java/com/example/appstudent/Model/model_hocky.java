package com.example.appstudent.Model;

public class model_hocky {
    private String tongsomon,giangvien,ngaybatdau,mammonhoc;

    public model_hocky(String tongsomon, String giangvien, String ngaybatdau) {
        this.tongsomon = tongsomon;
        this.giangvien = giangvien;
        this.ngaybatdau = ngaybatdau;
    }

    public String getTongsomon() {
        return tongsomon;
    }

    public void setTongsomon(String tongsomon) {
        this.tongsomon = tongsomon;
    }

    public String getGiangvien() {
        return giangvien;
    }

    public void setGiangvien(String giangvien) {
        this.giangvien = giangvien;
    }

    public String getNgaybatdau() {
        return ngaybatdau;
    }

    public void setNgaybatdau(String ngaybatdau) {
        this.ngaybatdau = ngaybatdau;
    }
}
