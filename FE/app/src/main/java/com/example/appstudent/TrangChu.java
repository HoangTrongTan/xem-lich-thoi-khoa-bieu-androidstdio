package com.example.appstudent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.appstudent.Model.GiaoVien;
import com.example.appstudent.Model.SinhVien;
import com.example.appstudent.Model.ThongBaoChung_model;
import com.example.appstudent.Tablayout_Fragment.ChitietThongbao;
import com.example.appstudent.Tablayout_Fragment.GiaoVien_Fragment;
import com.example.appstudent.Tablayout_Fragment.bottom_fragment_canhan;
import com.example.appstudent.Tablayout_Fragment.bottom_fragment_home;
import com.example.appstudent.Tablayout_Fragment.bottom_fragment_notifi;
import com.example.appstudent.Tablayout_Fragment.bottom_fragment_notifi1;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class TrangChu extends AppCompatActivity implements bottom_fragment_notifi1.IClickListener{

    public static SinhVien current_sinhvien;
    public static GiaoVien curGiaoVien;
    private MeowBottomNavigation bottomNavigation;
    private boolean ck = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);


        if(getIntent().getStringExtra("checked").equals("1")){
            ck = true;
            current_sinhvien = new SinhVien(getIntent().getStringExtra("tensv"),
                    getIntent().getStringExtra("masv"),
                    getIntent().getStringExtra("lop"),
                    getIntent().getStringExtra("NgaySinh"),
                    getIntent().getStringExtra("Dienthoai"),
                    getIntent().getStringExtra("Email"),
                    Integer.parseInt(getIntent().getStringExtra("GioiTinh"))
                    );
        }else{
            ck = false;
            curGiaoVien = new GiaoVien(
                    getIntent().getStringExtra("magv"),
                    getIntent().getStringExtra("tensv"),
                    getIntent().getStringExtra("tenkhoa"),
                    getIntent().getStringExtra("sdt")
            );
        }
        bottomNavigation = findViewById(R.id.bottomNavigation);

        bottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.notification));
        bottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.home));
        bottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.ic_baseline_person_24));
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.framelayout, new bottom_fragment_home())
                .commit();
        bottomNavigation.show(2,true);
        bottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                Fragment selectedFragment = null;
                String tag = null;
                switch (model.getId()){
                    case 1:
                        selectedFragment = getSupportFragmentManager().findFragmentByTag("notifi_fragment");
                        if (selectedFragment == null) {
                            selectedFragment = new bottom_fragment_notifi();
                            tag = "notifi_fragment";
                        }
                        break;
                    case 2:
                        selectedFragment = getSupportFragmentManager().findFragmentByTag("home_fragment");
                        if (selectedFragment == null) {
                            selectedFragment = new bottom_fragment_home();
                            tag = "home_fragment";
                        }
                        break;
                    case 3:
                        selectedFragment = getSupportFragmentManager().findFragmentByTag("canhan_fragment");
                        if (selectedFragment == null) {
                            tag = "canhan_fragment";
                            if(!ck){
                                selectedFragment = new GiaoVien_Fragment();
                                break;
                            }
                            selectedFragment = new bottom_fragment_canhan();
                            break;
                        }

                }
                if (selectedFragment != null) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, selectedFragment, tag).commit();
                }
                return null;
            }
        });


    }

    @Override
    public void passNotifi(ThongBaoChung_model model) {
        if(model != null){

            ChitietThongbao chitietThongbao = new ChitietThongbao();
            Bundle bundle = new Bundle();
            bundle.putSerializable("model",model);
            chitietThongbao.setArguments(bundle);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.framelayout, chitietThongbao).addToBackStack(ChitietThongbao.TAG)
                    .commit();
        }

    }
}