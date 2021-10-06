package com.erwin16mp.generadordecontraseas;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash_screen extends AppCompatActivity {

    Animation topAnimation, bottonAnimation;
    ImageView Logo;
    TextView Erwin, Correo;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);

        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottonAnimation = AnimationUtils.loadAnimation(this, R.anim.botton_animation);

        Logo = findViewById(R.id.logo);
        Erwin = findViewById(R.id.erwin);
        Correo =  findViewById(R.id.correo);

        Logo.setAnimation(topAnimation);
        Erwin.setAnimation(bottonAnimation);
        Correo.setAnimation(bottonAnimation);

        getWindow().setNavigationBarColor(Color.parseColor("#000000"));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Splash_screen.this, Index.class));
                finish();
            }
        },3000);
    }
}