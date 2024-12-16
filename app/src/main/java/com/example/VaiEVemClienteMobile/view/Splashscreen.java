package com.example.VaiEVemClienteMobile.view;

import android.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;



public class Splashscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Temporizador da Splash Screen
        new Handler().postDelayed(() -> {
            startActivity(new Intent(Splashscreen.this, MainActivity.class));
            finish(); // Fecha a Splash Screen
        }, 3000); // Duração de 3 segundos
    }
}

