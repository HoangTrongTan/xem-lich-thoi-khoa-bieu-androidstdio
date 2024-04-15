package com.example.appstudent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class Splash_Screen extends AppCompatActivity {
    private TextView name,slogan;
    private CircleImageView logo;
    private View topview1,topview2,topview3;
    private View bottomview1,bottomview2,bottomview3;
    private int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_FULLSCREEN|
                        View.SYSTEM_UI_FLAG_IMMERSIVE
        );
        setContentView(R.layout.activity_splash_screen);
        name = findViewById(R.id.textview1);
        slogan = findViewById(R.id.textview2);
        logo = findViewById(R.id.logo);

        topview1 = findViewById(R.id.topview1);
        topview2 = findViewById(R.id.topview2);
        topview3 = findViewById(R.id.topview3);

        bottomview1 = findViewById(R.id.bottomview1);
        bottomview2 = findViewById(R.id.bottomview2);
        bottomview3 = findViewById(R.id.bottomview3);

        Animation loAnimation = AnimationUtils.loadAnimation(Splash_Screen.this,R.anim.zoom_animation);
        Animation nameAnimation = AnimationUtils.loadAnimation(Splash_Screen.this,R.anim.zoom_animation);

        Animation topview1Animation = AnimationUtils.loadAnimation(Splash_Screen.this,R.anim.top_views_animation);
        Animation topview2Animation = AnimationUtils.loadAnimation(Splash_Screen.this,R.anim.top_views_animation);
        Animation topview3Animation = AnimationUtils.loadAnimation(Splash_Screen.this,R.anim.top_views_animation);

        Animation bottom1Animation = AnimationUtils.loadAnimation(Splash_Screen.this,R.anim.bottom_views_animation);
        Animation bottom2Animation = AnimationUtils.loadAnimation(Splash_Screen.this,R.anim.bottom_views_animation);
        Animation bottom3Animation = AnimationUtils.loadAnimation(Splash_Screen.this,R.anim.bottom_views_animation);

        topview1.startAnimation(topview1Animation);
        bottomview1.startAnimation(bottom1Animation);
        topview1Animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                topview2.setVisibility(View.VISIBLE);
                bottomview2.setVisibility(View.VISIBLE);

                topview2.startAnimation(topview2Animation);
                bottomview2.startAnimation(bottom2Animation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        topview2Animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                topview3.setVisibility(View.VISIBLE);
                bottomview3.setVisibility(View.VISIBLE);

                topview3.startAnimation(topview3Animation);
                bottomview3.startAnimation(bottom3Animation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        topview3Animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                logo.setVisibility(View.VISIBLE);
                logo.startAnimation(loAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        loAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                name.setVisibility(View.VISIBLE);
                name.startAnimation(nameAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        nameAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                slogan.setVisibility(View.VISIBLE);
                final String animatext = slogan.getText().toString();
                slogan.setText("");

                count = 0;
                new CountDownTimer(animatext.length() * 100, 100){

                    @Override
                    public void onTick(long millisUntilFinished) {
                        slogan.setText(slogan.getText().toString() + animatext.charAt(count));
                        count ++;
                    }

                    @Override
                    public void onFinish() {

                    }
                }.start();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Splash_Screen.this,MainActivity.class));
                finish();
            }
        },7000);
    }
}