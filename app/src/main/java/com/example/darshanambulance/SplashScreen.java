package com.example.darshanambulance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.darshanambulance.activity.Dashboard;
import com.example.darshanambulance.utility.preferences;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //This method will be executed once the timer is over
                // Start your app main activity
                // if user already loged in then this condition will fire
                if (preferences.getDataLogin(getApplicationContext())) {
                    if (preferences.getDataAs(getApplicationContext()).equals("user")) {
                        startActivity(new Intent(getApplicationContext(), Dashboard.class));
                        finish();
                    }
                }else {
                    Intent i = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        }, 3000);
    }
}