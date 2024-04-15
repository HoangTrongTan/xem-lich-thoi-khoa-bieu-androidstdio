package com.example.appstudent.Model;

public class HocKy {
    private String monhoc,mamonhoc,giangvien,tongsotiet,thoigiannhoc;
    private boolean exxpandble;

    public HocKy(String monhoc, String mamonhoc, String giangvien, String tongsotiet, String thoigiannhoc, boolean exxpandble) {
        this.monhoc = monhoc;
        this.mamonhoc = mamonhoc;
        this.giangvien = giangvien;
        this.tongsotiet = tongsotiet;
        this.thoigiannhoc = thoigiannhoc;
        this.exxpandble = exxpandble;
    }

    public boolean isExxpandble() {
        return exxpandble;
    }

    public void setExxpandble(boolean exxpandble) {
        this.exxpandble = exxpandble;
    }

    public String getMonhoc() {
        return monhoc;
    }

    public void setMonhoc(String monhoc) {
        this.monhoc = monhoc;
    }

    public String getMamonhoc() {
        return mamonhoc;
    }

    public void setMamonhoc(String mamonhoc) {
        this.mamonhoc = mamonhoc;
    }

    public String getGiangvien() {
        return giangvien;
    }

    public void setGiangvien(String giangvien) {
        this.giangvien = giangvien;
    }

    public String getTongsotiet() {
        return tongsotiet;
    }

    public void setTongsotiet(String tongsotiet) {
        this.tongsotiet = tongsotiet;
    }

    public String getThoigiannhoc() {
        return thoigiannhoc;
    }

    public void setThoigiannhoc(String thoigiannhoc) {
        this.thoigiannhoc = thoigiannhoc;
    }
}
