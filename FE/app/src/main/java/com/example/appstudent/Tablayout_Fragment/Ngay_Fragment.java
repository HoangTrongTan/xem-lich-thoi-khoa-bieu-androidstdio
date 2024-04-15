package com.example.appstudent.Tablayout_Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appstudent.Controller.Controller;
import com.example.appstudent.Event_s.Event_onclick;
import com.example.appstudent.I_OnclickLongItem_of_RecycleView.OnClickLongItem;
import com.example.appstudent.MainActivity;
import com.example.appstudent.Model.model_ngay;
import com.example.appstudent.Model.model_ngay_Parent;
import com.example.appstudent.R;
import com.example.appstudent.TrangChu;
import com.example.appstudent.adapter_type.Adapter_ngay;
import com.example.appstudent.adapter_type.Adapter_ngay_Parent;

import java.util.ArrayList;

public class Ngay_Fragment extends Fragment {

    private ArrayList<model_ngay_Parent> modelNgayParents = new ArrayList<>();
    private Adapter_ngay_Parent adapterNgayParent;
    RecyclerView recyclerView_ngay;
    Button chonngay;
    TextView ngayhientai,thuhientai;
    private String lop = "";

    public Ngay_Fragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lop = getArguments().getString("lop");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ngay_, container, false);
            Controller.turn_off_SSL();
            chonngay = view.findViewById(R.id.chonngay);
            ngayhientai = view.findViewById(R.id.ngay);
            thuhientai = view.findViewById(R.id.thu);
        recyclerView_ngay = view.findViewById(R.id.recycle_ngay);

            Event_onclick.click_lich(chonngay, view.getContext(), thuhientai,ngayhientai,recyclerView_ngay,lop);


        chonngay.setText(Controller.get_Month_Year_present());
        thuhientai.setText("Thứ "+Controller.date_month_present[0]);
        ngayhientai.setText("Ngày "+Controller.date_month_present[1]);


        //set recycle parent

        adapterNgayParent = new Adapter_ngay_Parent(view.getContext());

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(view.getContext(),RecyclerView.VERTICAL,false);
        recyclerView_ngay.setLayoutManager(linearLayoutManager1);

        adapterNgayParent.setData(modelNgayParents);
        recyclerView_ngay.setAdapter(adapterNgayParent);
        if(TrangChu.current_sinhvien != null){
            Controller.getJsonArray_ngay(view.getContext(),recyclerView_ngay,lop,"");
            adapterNgayParent.notifyDataSetChanged();
        }
        else{
            Controller.getJsonArray_ngay(view.getContext(),recyclerView_ngay,"",TrangChu.curGiaoVien.getHoTen());
            adapterNgayParent.notifyDataSetChanged();
        }


        return view;
    }

}