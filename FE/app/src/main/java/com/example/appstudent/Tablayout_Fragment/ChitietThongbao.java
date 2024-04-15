package com.example.appstudent.Tablayout_Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.appstudent.Model.ThongBaoChung_model;
import com.example.appstudent.R;
public class ChitietThongbao extends Fragment {
    public static final String TAG = ChitietThongbao.class.getName();
    public ChitietThongbao() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chitiet_thongbao, container, false);
        ThongBaoChung_model obj = (ThongBaoChung_model) getArguments().getSerializable("model");

            TextView TieuDe,thoigian,NoiDung;
            TieuDe = view.findViewById(R.id.TieuDe);
            thoigian= view.findViewById(R.id.thoigian);
            NoiDung = view.findViewById(R.id.NoiDung);
            ImageButton trove = view.findViewById(R.id.trove);
            TieuDe.setText(obj.getTitle());
            thoigian.setText(obj.getThoigianhieuluc());
            NoiDung.setText(obj.getNoidung());
            trove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (requireFragmentManager() != null) {
                        requireFragmentManager().popBackStack();
                    }
                }
            });
        return view;
    }
}