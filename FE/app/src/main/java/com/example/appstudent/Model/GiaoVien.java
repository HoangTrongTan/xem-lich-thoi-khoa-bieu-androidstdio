package com.example.appstudent.Model;

public class GiaoVien extends Person{
    private String tenkhoa,sdt;
     public GiaoVien(String hoTen, String ma, String tenkhoa, String sdt) {
        super(hoTen, ma);
        this.tenkhoa = tenkhoa;
        this.sdt = sdt;
    }

    public String getTenkhoa() {
        return tenkhoa;
    }

    public void setTenkhoa(String tenkhoa) {
        this.tenkhoa = tenkhoa;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
}
