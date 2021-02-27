package com.example.proect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        try {
            Thread.sleep(4000);//Приостанавливает поток на 1 секунду
            Intent i;
            i = new Intent(SplashScreen.this,MainActivity.class );
            startActivity(i);
        }catch (Exception e){

        }

    }
}