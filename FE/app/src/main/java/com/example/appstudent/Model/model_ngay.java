package com.example.appstudent.Model;

public class model_ngay {
    private String tietbatdau,tietketthuc;
    private String sophong,tenmon,hoten,tenlop;

    public model_ngay(String tietbatdau, String tietketthuc, String sophong, String tenmon, String hoten, String tenlop) {
        this.tietbatdau = tietbatdau;
        this.tietketthuc = tietketthuc;
        this.sophong = sophong;
        this.tenmon = tenmon;
        this.hoten = hoten;
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

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getTenlop() {
        return tenlop;
    }

    public void setTenlop(String tenlop) {
        this.tenlop = tenlop;
    }
}
