package com.example.appstudent.Tablayout_Fragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appstudent.Model.ThongBaoChung_model;
import com.example.appstudent.R;

public class bottom_fragment_notifi extends Fragment  {
    private TextView tabone,tabtwo;

    private int selectedTabNumber = 1;
    public bottom_fragment_notifi() {

    }
    private bottom_fragment_notifi1 notifi1 = new bottom_fragment_notifi1();
    private bottom_fragment_notifi2 notifi2 = new bottom_fragment_notifi2();
    private ChitietThongbao chitiet = new ChitietThongbao();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_notifi, container, false);
        tabone = view.findViewById(R.id.tabone);
        tabtwo = view.findViewById(R.id.tabtwo);


        getChildFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_container, notifi1).commit();

        tabone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selecTab(1);
            }
        });
        tabtwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selecTab(2);
            }
        });
        return view;
    }
    private void selecTab(int tabnumber){
        TextView selectedTextView;
        TextView textviewone;

        if(tabnumber == 1){
            selectedTextView = tabone;
            textviewone = tabtwo;
            getChildFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragment_container, notifi1).commit();
        }
        else{
            selectedTextView = tabtwo;
            textviewone = tabone;
            getChildFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragment_container, notifi2).commit();
        }
        float slideTo = (tabnumber - selectedTabNumber) * selectedTextView.getWidth();
        TranslateAnimation translateAnimation = new TranslateAnimation(0,slideTo,0,0);
        translateAnimation.setDuration(100);
        if(selectedTabNumber == 1){
            tabone.startAnimation(translateAnimation);
        }else{
            tabtwo.startAnimation(translateAnimation);
        }
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                selectedTextView.setBackgroundResource(R.drawable.round_back_white);
                selectedTextView.setTypeface(null, Typeface.BOLD);
                selectedTextView.setTextColor(Color.BLACK);

                textviewone.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                textviewone.setTextColor(Color.parseColor("#FFFfff"));
                textviewone.setTypeface(null,Typeface.NORMAL);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
//1700515
            }
        });
        selectedTabNumber = tabnumber;
    }

}