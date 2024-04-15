package com.example.appstudent.Model;

import java.util.ArrayList;

public class model_Tuan {
    private String thu;
    private ArrayList<model_Tuan_Parent> tuan;
    private boolean expandbel = false;

    public model_Tuan(String thu, ArrayList<model_Tuan_Parent> tuan, boolean expandbel) {
        this.thu = thu;
        this.tuan = tuan;
        this.expandbel = expandbel;
    }

    public String getThu() {
        return thu;
    }

    public void setThu(String thu) {
        this.thu = thu;
    }

    public ArrayList<model_Tuan_Parent> getTuan() {
        return tuan;
    }

    public void setTuan(ArrayList<model_Tuan_Parent> tuan) {
        this.tuan = tuan;
    }

    public boolean isExpandbel() {
        return expandbel;
    }

    public void setExpandbel(boolean expandbel) {
        this.expandbel = expandbel;
    }
}
