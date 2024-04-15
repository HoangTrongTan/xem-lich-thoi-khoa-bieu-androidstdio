package com.example.appstudent.Model;

import java.util.ArrayList;

public class model_Tuan_Parent {
    private String Thu;
    private ArrayList<model_Tuan_child> parent;

    public model_Tuan_Parent(String thu, ArrayList<model_Tuan_child> parent) {
        Thu = thu;
        this.parent = parent;
    }

    public String getThu() {
        return Thu;
    }

    public void setThu(String thu) {
        Thu = thu;
    }

    public ArrayList<model_Tuan_child> getParent() {
        return parent;
    }

    public void setParent(ArrayList<model_Tuan_child> parent) {
        this.parent = parent;
    }
}
