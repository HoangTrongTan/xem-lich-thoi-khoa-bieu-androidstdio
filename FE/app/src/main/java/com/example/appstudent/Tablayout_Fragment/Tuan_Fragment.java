package com.example.appstudent.Tablayout_Fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.appstudent.Adapter_Tuan.Adapter_tuan;
import com.example.appstudent.Controller.Controller;
import com.example.appstudent.Event_s.Event_onclick;
import com.example.appstudent.MainActivity;
import com.example.appstudent.Model.model_Tuan;
import com.example.appstudent.Model.model_Tuan_Parent;
import com.example.appstudent.Model.model_Tuan_child;
import com.example.appstudent.R;
import com.example.appstudent.TrangChu;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;


public class Tuan_Fragment extends Fragment {
    ArrayList<model_Tuan> tuanlist;
    Adapter_tuan adapterTuan;
    RecyclerView recyclerView;
    Button chontuan;
    int dayOfWeek_present = 0;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Calendar calendar = Calendar.getInstance();
        dayOfWeek_present = calendar.get(Calendar.DAY_OF_WEEK);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_tuan_, container, false);
            chontuan = view.findViewById(R.id.chontuan);
            recyclerView = view.findViewById(R.id.recycle_tuan);
            chontuan.setText(getArguments().getString("lop"));



        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);



            tuanlist = new ArrayList<>();
            if(TrangChu.current_sinhvien != null){
                Controller.get_Week_present(getContext(),tuanlist,chontuan,TrangChu.current_sinhvien.getLop(),dayOfWeek_present,"");
                Event_onclick.click_lich_week(chontuan, view.getContext(), recyclerView,TrangChu.current_sinhvien.getLop());
            }else{
                Controller.get_Week_present(getContext(),tuanlist,chontuan,"",dayOfWeek_present,TrangChu.curGiaoVien.getHoTen());
                Event_onclick.click_lich_week(chontuan, view.getContext(), recyclerView,"");
            }

            adapterTuan = new Adapter_tuan(tuanlist);
            recyclerView.setAdapter(adapterTuan);
            recyclerView.setHasFixedSize(true);

        return view;
    }
}