package com.example.appstudent.Model;

import com.example.appstudent.R;

import java.io.Serializable;

public class ThongBaoChung_model implements Serializable {
    private String title,thoigianhieuluc,noidung;
    private int checkTypeNotifi;

    public ThongBaoChung_model(String title, String thoigianhieuluc, String noidung, int checkTypeNotifi) {
        this.title = title;
        this.thoigianhieuluc = thoigianhieuluc;
        this.noidung = noidung;
        this.checkTypeNotifi = setImageNotifi(checkTypeNotifi);
    }
    public int setImageNotifi(int i){
        if(i == 0){
            return  R.drawable.thongbao2;
        }else if(i == 1){
            return R.drawable.thongbao3;
        }else{
            return R.drawable.thongbaodangky;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThoigianhieuluc() {
        return thoigianhieuluc;
    }

    public void setThoigianhieuluc(String thoigianhieuluc) {
        this.thoigianhieuluc = thoigianhieuluc;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public int getCheckTypeNotifi() {
        return checkTypeNotifi;
    }

    public void setCheckTypeNotifi(int checkTypeNotifi) {
        this.checkTypeNotifi = checkTypeNotifi;
    }
}
