package com.example.appstudent.Tablayout_Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.appstudent.Controller.Controller;
import com.example.appstudent.Event_s.Event_onclick;
import com.example.appstudent.MainActivity;
import com.example.appstudent.Model.HocKy;
import com.example.appstudent.R;
import com.example.appstudent.TrangChu;
import com.example.appstudent.adapter_type.Adapater_HocKy;

import java.util.ArrayList;

public class HocKy_Fragment extends Fragment {
    Button chonhocky;
    RecyclerView recyclerView;
    private ArrayList<HocKy> listhocky = new ArrayList<>();
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hoc_ky_, container, false);
            chonhocky = view.findViewById(R.id.chonhocky);

            recyclerView = view.findViewById(R.id.recycle_hocky);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
            recyclerView.setLayoutManager(linearLayoutManager);

            if(TrangChu.current_sinhvien != null){
                Controller.getHocKy_Json(getContext(),listhocky,"2",getArguments().getString("lop"),recyclerView,"");
                Event_onclick.hocki(chonhocky, view.getContext(),listhocky,getArguments().getString("lop"),recyclerView,"");
            }else{
                Controller.getHocKy_Json(getContext(),listhocky,"2",getArguments().getString("lop"),recyclerView,TrangChu.curGiaoVien.getHoTen());
                Event_onclick.hocki(chonhocky, view.getContext(),listhocky,getArguments().getString("lop"),recyclerView,TrangChu.curGiaoVien.getHoTen());
            }
            recyclerView.setHasFixedSize(false);

        return view;
    }
}