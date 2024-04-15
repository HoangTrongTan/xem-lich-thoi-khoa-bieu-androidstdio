package com.example.appstudent.adapter_type;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.appstudent.Tablayout_Fragment.HocKy_Fragment;
import com.example.appstudent.Tablayout_Fragment.Ngay_Fragment;
import com.example.appstudent.Tablayout_Fragment.Tuan_Fragment;

public class Adapter_Tab extends FragmentStateAdapter {
    private String lop = "";
    public Adapter_Tab(@NonNull FragmentActivity fragmentActivity,String lop) {
        super(fragmentActivity);
        this.lop = lop;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                Ngay_Fragment ngay_fragment = new Ngay_Fragment();
                Bundle bundle = new Bundle();
                bundle.putString("lop",this.lop);
                ngay_fragment.setArguments(bundle);
                return ngay_fragment;
            case 1:
                Tuan_Fragment tuan_fragment = new Tuan_Fragment();
                Bundle bundle1 = new Bundle();
                bundle1.putString("lop",this.lop);
                tuan_fragment.setArguments(bundle1);
                return tuan_fragment;
            case 2:
                HocKy_Fragment hocKy_fragment = new HocKy_Fragment();
                Bundle bundle2 = new Bundle();
                bundle2.putString("lop",this.lop);
                hocKy_fragment.setArguments(bundle2);
                return hocKy_fragment;
            default:
                Ngay_Fragment ngay_fragment0 = new Ngay_Fragment();
                Bundle bundle0 = new Bundle();
                bundle0.putString("lop",this.lop);
                ngay_fragment0.setArguments(bundle0);
                return ngay_fragment0;
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
