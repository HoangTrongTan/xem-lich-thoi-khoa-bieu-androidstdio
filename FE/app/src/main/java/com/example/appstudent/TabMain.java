package com.example.appstudent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appstudent.adapter_type.Adapter_Tab;
import com.google.android.material.tabs.TabLayout;

public class TabMain extends AppCompatActivity {
    private TextView namesinhvien;
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    Adapter_Tab adapter;
    private String lop = "";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_main);
//        Toast.makeText(this,TrangChu.current_sinhvien.getLop() , Toast.LENGTH_SHORT).show();

        namesinhvien = findViewById(R.id.namesinhvien);
        tabLayout = findViewById(R.id.tab_layout);
        viewPager2 = findViewById(R.id.viewpager);
        ImageButton btnback = findViewById(R.id.btnback);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        if(TrangChu.current_sinhvien != null){
            adapter = new Adapter_Tab(this,TrangChu.current_sinhvien.getLop());
            namesinhvien.setText(TrangChu.current_sinhvien.getHoTen());
        }else{
            adapter = new Adapter_Tab(this,TrangChu.curGiaoVien.getMa());
            namesinhvien.setText(TrangChu.curGiaoVien.getMa());
        }

        viewPager2.setAdapter(adapter);



        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });
    }
}