package com.example.appstudent.Model;

public class Person {
    private String HoTen,Ma;

    public Person() {
    }

    public Person(String hoTen, String ma) {
        HoTen = hoTen;
        Ma = ma;
    }
    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public String getMa() {
        return Ma;
    }

    public void setMa(String ma) {
        Ma = ma;
    }
}
