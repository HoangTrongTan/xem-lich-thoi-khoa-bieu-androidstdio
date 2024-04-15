package com.example.appstudent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.appstudent.Controller.QuyCheControll;

public class QuyChe_QuyDinh extends AppCompatActivity {
    private RecyclerView recycle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quy_che_quy_dinh);
        ImageButton btnback = findViewById(R.id.btnback);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        recycle = findViewById(R.id.recycle);
        recycle.setLayoutManager(new LinearLayoutManager(QuyChe_QuyDinh.this,RecyclerView.VERTICAL,false));
        QuyCheControll.getQuyChe(QuyChe_QuyDinh.this,recycle);
    }
}