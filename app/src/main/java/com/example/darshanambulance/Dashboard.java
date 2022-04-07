package com.example.darshanambulance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Dashboard extends AppCompatActivity {

    private Button logout_btn;
    private CardView find_ambulance,ambulancemap,contactsupport;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        logout_btn = (Button) findViewById(R.id.logout_btn);
        find_ambulance = (CardView) findViewById(R.id.find_ambulance);
        ambulancemap = (CardView) findViewById(R.id.ambulancemap);
        contactsupport = (CardView) findViewById(R.id.contactsupport);


        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Dashboard.this,MainActivity.class));
            }
        });

        find_ambulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Dashboard.this,FindAmbulance.class));
            }
        });

        ambulancemap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Dashboard.this,AmbulanceMap.class));
            }
        });

        contactsupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Dashboard.this,ContactSupport.class));
            }
        });




    }
}