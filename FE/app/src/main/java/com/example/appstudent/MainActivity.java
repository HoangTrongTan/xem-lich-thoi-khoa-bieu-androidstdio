package com.example.appstudent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appstudent.Controller.Controller;
import com.example.appstudent.Event_s.Event_onclick;
import com.example.appstudent.I_OnclickLongItem_of_RecycleView.OnClickLongItem;
import com.example.appstudent.Model.model_ngay;
import com.example.appstudent.adapter_type.Adapter_ngay;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button NhapMaSinhVien;
    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Controller.turn_off_SSL();
        NhapMaSinhVien = findViewById(R.id.NhapMaSinhVien);
        NhapMaSinhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Controller.opendialog_NhapMaSinhVien(MainActivity.this,Gravity.CENTER);
            }
        });
    }
    private void setting_toast(){
        Toast toast = new Toast(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_toast,(ViewGroup) findViewById(R.id.layout_custom_toast));
        toast.setView(view);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }



}