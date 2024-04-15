package com.example.appstudent.Tablayout_Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appstudent.MainActivity;
import com.example.appstudent.R;
import com.example.appstudent.TrangChu;

public class GiaoVien_Fragment extends Fragment {
    private TextView tengiaovien,magv,contentdt,contentkhoa;
    private Button btn_dangxuat;
    public GiaoVien_Fragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_giao_vien_, container, false);
                tengiaovien = view.findViewById(R.id.tengiaovien);
        magv = view.findViewById(R.id.magv);
        btn_dangxuat = view.findViewById(R.id.btn_dangxuat);
        contentdt = view.findViewById(R.id.content);
        contentkhoa = view.findViewById(R.id.contentkhoa);

        tengiaovien.setText(TrangChu.curGiaoVien.getMa());
        magv.setText(TrangChu.curGiaoVien.getHoTen());
        contentdt.setText(TrangChu.curGiaoVien.getSdt());
        contentkhoa.setText(TrangChu.curGiaoVien.getTenkhoa());
        btn_dangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TrangChu.curGiaoVien = null;
                startActivity(new Intent(getContext(), MainActivity.class));
            }
        });
        return view;
    }
}