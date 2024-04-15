package com.example.appstudent.Event_s;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.appstudent.Controller.Controller;
import com.example.appstudent.Model.HocKy;
import com.example.appstudent.R;
import com.example.appstudent.Tablayout_Fragment.Ngay_Fragment;
import com.example.appstudent.TrangChu;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Calendar;

public class Event_onclick {

    public static void hocki(Button chonhocky, Context context, ArrayList<HocKy> list_hocky,String lop,RecyclerView recyclerView,String magv){
        chonhocky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
                View bottomSheetView = LayoutInflater.from(context).inflate(R.layout.layout_bottomsheet, null);
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
                Button hocky1 = bottomSheetDialog.findViewById(R.id.hocky1);
                Button hocky2 = bottomSheetDialog.findViewById(R.id.hocky2);
                hocky1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        list_hocky.clear();
                        Controller.getHocKy_Json(context,list_hocky,"1",lop,recyclerView,magv);
                        Controller.startAnimaton(context,recyclerView);
                        chonhocky.setText(hocky1.getText());
                        bottomSheetDialog.dismiss();
                    }
                });
                hocky2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        list_hocky.clear();
                        Controller.getHocKy_Json(context,list_hocky,"2",lop,recyclerView,magv);
                        Controller.startAnimaton(context,recyclerView);
                        chonhocky.setText(hocky2.getText());
                        bottomSheetDialog.dismiss();
                    }
                });
            }
        });

    }
    public static void click_lich(Button button, Context context, TextView text_thu, TextView txt_ngay, RecyclerView recyclerView, String lop){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                // Khởi tạo DatePickerDialog và hiển thị nó

                DatePickerDialog datePickerDialog = new DatePickerDialog(context,R.style.CustomDatePickerDialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Xử lý ngày được chọn ở đây
                        c.set(year,monthOfYear,dayOfMonth);
                        int thang = c.get(Calendar.MONTH);
                        int nam = c.get(Calendar.YEAR);
                        int ngay = c.get(Calendar.DAY_OF_MONTH);
                        int thu = c.get(Calendar.DAY_OF_WEEK);
                        if(text_thu != null && txt_ngay != null){

                            text_thu.setText("Thứ "+thu);
                            if(thu == 1){
                                text_thu.setText("CN");
                            }
                            txt_ngay.setText("Ngày " + ngay);
                        }
                        button.setText("Tháng "+(thang+1)+" "+nam);
                        Controller.startAnimaton(context,text_thu);
                        Controller.startAnimaton(context,txt_ngay);
                        String y_m_d = String.valueOf(nam) +"-"+String.valueOf(thang+1)+"-"+String.valueOf(ngay);
                        if(thang+1 < 10 && ngay < 10){
                            y_m_d = String.valueOf(nam) +"-0"+String.valueOf(thang+1)+"-0"+String.valueOf(ngay);
                        }
                        if(TrangChu.current_sinhvien != null) {
                            Controller.get_date_of_choose(context, y_m_d, recyclerView, lop,"");
                        }else{
                            Controller.get_date_of_choose(context, y_m_d, recyclerView, null,TrangChu.curGiaoVien.getHoTen());
                        }
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }
    public static void click_lich_week(Button button, Context context,RecyclerView recyclerView, String lop){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                // Khởi tạo DatePickerDialog và hiển thị nó

                DatePickerDialog datePickerDialog = new DatePickerDialog(context,R.style.CustomDatePickerDialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Xử lý ngày được chọn ở đây
                        c.set(year,monthOfYear,dayOfMonth);
                        int thang = c.get(Calendar.MONTH);
                        int nam = c.get(Calendar.YEAR);
                        int ngay = c.get(Calendar.DAY_OF_MONTH);

                        button.setText("Tháng "+(thang+1)+" "+nam);
                        Controller.startAnimaton_circle(context,recyclerView);
                        String y_m_d = null;
                        if(thang < 10){
                            y_m_d = String.valueOf(nam) +"-0"+String.valueOf(thang+1)+"-"+String.valueOf(ngay);
                        }else if(thang < 10 && ngay < 10){
                            y_m_d = String.valueOf(nam) +"-0"+String.valueOf(thang+1)+"-0"+String.valueOf(ngay);
                        }else if(ngay < 10){
                            y_m_d = String.valueOf(nam) +"-"+String.valueOf(thang+1)+"-0"+String.valueOf(ngay);
                        }else{
                            y_m_d = String.valueOf(nam) +"-"+String.valueOf(thang+1)+"-"+String.valueOf(ngay);
                        }


                        if(TrangChu.current_sinhvien != null){
                            Controller.get_Week_of_Choose(context,y_m_d,lop,recyclerView,"");
                        }else{
                            Controller.get_Week_of_Choose(context,y_m_d,null,recyclerView,TrangChu.curGiaoVien.getHoTen());
                        }

                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }
}
