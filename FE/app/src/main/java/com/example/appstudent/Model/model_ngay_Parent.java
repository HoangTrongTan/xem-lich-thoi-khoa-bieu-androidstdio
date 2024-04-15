package com.example.appstudent.Model;

import java.util.ArrayList;

public class model_ngay_Parent {
    private String title;
    private ArrayList<model_ngay> list;

    public model_ngay_Parent(String title, ArrayList<model_ngay> list) {
        this.title = title;
        this.list = list;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<model_ngay> getList() {
        return list;
    }

    public void setList(ArrayList<model_ngay> list) {
        this.list = list;
    }
}
