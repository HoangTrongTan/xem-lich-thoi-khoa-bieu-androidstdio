package com.example.appstudent.Model;

public class model_Tuan_child {
    private String tietbatdau,tietketthuc,sophong,tenmon,tengiaovien,tenlop;
    private String ngaybatdau;

    public String getNgaybatdau() {
        return ngaybatdau;
    }

    public void setNgaybatdau(String ngaybatdau) {
        this.ngaybatdau = ngaybatdau;
    }

    public model_Tuan_child(String tietbatdau, String tietketthuc, String sophong, String tenmon, String tengiaovien, String tenlop, String ngaybatdau) {
        this.tietbatdau = tietbatdau;
        this.tietketthuc = tietketthuc;
        this.sophong = sophong;
        this.tenmon = tenmon;
        this.tengiaovien = tengiaovien;
        this.tenlop = tenlop;
        this.ngaybatdau = ngaybatdau;
    }

    public model_Tuan_child(String tietbatdau, String tietketthuc, String sophong, String tenmon, String tengiaovien, String tenlop) {
        this.tietbatdau = tietbatdau;
        this.tietketthuc = tietketthuc;
        this.sophong = sophong;
        this.tenmon = tenmon;
        this.tengiaovien = tengiaovien;
        this.tenlop = tenlop;
    }

    public String getTietbatdau() {
        return tietbatdau;
    }

    public void setTietbatdau(String tietbatdau) {
        this.tietbatdau = tietbatdau;
    }

    public String getTietketthuc() {
        return tietketthuc;
    }

    public void setTietketthuc(String tietketthuc) {
        this.tietketthuc = tietketthuc;
    }

    public String getSophong() {
        return sophong;
    }

    public void setSophong(String sophong) {
        this.sophong = sophong;
    }

    public String getTenmon() {
        return tenmon;
    }

    public void setTenmon(String tenmon) {
        this.tenmon = tenmon;
    }

    public String getTengiaovien() {
        return tengiaovien;
    }

    public void setTengiaovien(String tengiaovien) {
        this.tengiaovien = tengiaovien;
    }

    public String getTenlop() {
        return tenlop;
    }

    public void setTenlop(String tenlop) {
        this.tenlop = tenlop;
    }
}
