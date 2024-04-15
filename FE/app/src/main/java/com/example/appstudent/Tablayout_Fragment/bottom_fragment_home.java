package com.example.appstudent.Tablayout_Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.appstudent.GioiTheu;
import com.example.appstudent.Model.Photo;
import com.example.appstudent.QuyChe_QuyDinh;
import com.example.appstudent.R;
import com.example.appstudent.TabMain;
import com.example.appstudent.TrangChu;
import com.example.appstudent.adapter_type.PhottoAdapter;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;
import pl.droidsonroids.gif.GifImageView;


public class bottom_fragment_home extends Fragment {
    private ImageButton btnLichHoc,btnGioiThieu,btnQuyCheQuyDinh;
    private TextView TenSinhVien;
    private ViewPager2 viewPager2;
    private GifImageView imgegif;
    private List<Photo> list_photo = new ArrayList<>();
    private CircleIndicator3 circleIndicator3;
    private Handler handler = new Handler(Looper.getMainLooper());
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int curenitem = viewPager2.getCurrentItem();
            if(curenitem == list_photo.size()-1){
                viewPager2.setCurrentItem(0);
            }else{
                viewPager2.setCurrentItem(curenitem+1);
            }
        }
    };
    public bottom_fragment_home() {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_bottom_home, container, false);
        AnhXa(view);

        viewPager2 = view.findViewById(R.id.viewpagerHome);
        circleIndicator3 = view.findViewById(R.id.circleIndicator3Home);
        imgegif = view.findViewById(R.id.imgegif);
        viewPager2.setOffscreenPageLimit(3);// có nghĩa là hieent thị 3 cái 1 ở giữa và 2 bên
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {//set kích thước cho 2 cái tahwngf bên cạch th giữa lớn hơn
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.5f + r * 0.5f);
            }
        });
        viewPager2.setPageTransformer(compositePageTransformer);

        list_photo.add(new Photo(R.drawable.saodo1));
        list_photo.add(new Photo(R.drawable.thucpham));
        list_photo.add(new Photo(R.drawable.totnghiep1));
        list_photo.add(new Photo(R.drawable.totnghiep2));
        list_photo.add(new Photo(R.drawable.totnghiep3));
        list_photo.add(new Photo(R.drawable.totnghiep4));
        PhottoAdapter adapter = new PhottoAdapter(list_photo);
        viewPager2.setAdapter(adapter);
        circleIndicator3.setViewPager(viewPager2);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable,3000);
            }
        });
        return view;

    }
    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable, 3000);
    }
    private void AnhXa(View view){
        btnLichHoc = view.findViewById(R.id.btnLichHoc);
        btnGioiThieu = view.findViewById(R.id.btnGioiThieu);
        btnGioiThieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), GioiTheu.class));
            }
        });

        btnQuyCheQuyDinh = view.findViewById(R.id.btnQuyCheQuyDinh);
        btnQuyCheQuyDinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),QuyChe_QuyDinh.class  ));
            }
        });
        TenSinhVien = view.findViewById(R.id.TenSinhVien);

        if(TrangChu.current_sinhvien != null){
            TenSinhVien.setText("Hello "+ TrangChu.current_sinhvien.getHoTen()+" !");
        }
        else{
            TenSinhVien.setText("Hello "+ TrangChu.curGiaoVien.getMa()+" !");
        }


        btnLichHoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.getContext().startActivity(new Intent(view.getContext(),TabMain.class));
            }
        });
    }
}