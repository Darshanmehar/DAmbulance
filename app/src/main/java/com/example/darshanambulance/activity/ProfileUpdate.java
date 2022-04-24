package com.example.darshanambulance.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.darshanambulance.R;
import com.example.darshanambulance.utility.preferences;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileUpdate extends AppCompatActivity {

    TextInputEditText name,phone,address,age;
    Button update_profile;
    String email = "";
    String userid = "";
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_update);
        getSupportActionBar().setTitle("Update Profile");
        name = (TextInputEditText) findViewById(R.id.name);
        phone = (TextInputEditText) findViewById(R.id.phone);
        address = (TextInputEditText) findViewById(R.id.address);
        age = (TextInputEditText) findViewById(R.id.age);
        update_profile = (Button) findViewById(R.id.update_profile);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        email = preferences.getDataEmail(getApplicationContext());
        userid = preferences.getDataUserid(getApplicationContext());
        setUserData();
        update_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String update_name = name.getText().toString();
                String update_phone = phone.getText().toString();
                String update_address = address.getText().toString();
                String update_age = age.getText().toString();
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
                reference.child(userid).child("address").setValue(update_address);
                reference.child(userid).child("phone").setValue(update_phone);
                reference.child(userid).child("fullname").setValue(update_name);
                reference.child(userid).child("age").setValue(update_age);
                preferences.setDataFullname(ProfileUpdate.this,update_name);
                startActivity(new Intent(ProfileUpdate.this, UpdateSuccess.class));
            }
        });
    }

    private void setUserData() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot mainSnapshot) {
                String uname = mainSnapshot.child(userid).child("fullname").getValue(String.class);
                String uphone = mainSnapshot.child(userid).child("phone").getValue(String.class);
                String uage = mainSnapshot.child(userid).child("age").getValue(String.class);
                String uaddress = mainSnapshot.child(userid).child("address").getValue(String.class);
                name.setText(uname);
                phone.setText(uphone);
                age.setText(uage);
                address.setText(uaddress);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}