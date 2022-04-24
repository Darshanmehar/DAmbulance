package com.example.darshanambulance.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.darshanambulance.R;
import com.example.darshanambulance.model.Contacts;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ContactSupport extends AppCompatActivity {

    TextInputEditText name,subject,phone,message;
    DatabaseReference databaseReference;
    Button submit;
    ProgressBar progressBar;
    LinearLayout layoutcontact,submit_li;
    TextView successmsg;
    Button gohome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_support);
        name = (TextInputEditText) findViewById(R.id.name);
        subject = (TextInputEditText) findViewById(R.id.subject);
        phone = (TextInputEditText) findViewById(R.id.phone);
        message = (TextInputEditText) findViewById(R.id.message);
        submit = (Button) findViewById(R.id.submit);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        layoutcontact = (LinearLayout) findViewById(R.id.layoutcontact);
        submit_li= (LinearLayout) findViewById(R.id.submit_li);
        successmsg = (TextView) findViewById(R.id.successmsg);
        gohome = (Button) findViewById(R.id.gohome);



        databaseReference = FirebaseDatabase.getInstance().getReference().child("Contacts");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                submitContact();
            }
        });

        gohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ContactSupport.this,Dashboard.class));
            }
        });
    }

    private void submitContact() {
        String s_name = name.getText().toString();
        String s_subject = subject.getText().toString();
        String s_phone = phone.getText().toString();
        String s_message = message.getText().toString();
        Contacts contacts = new Contacts(s_name,s_subject,s_phone,s_message);
        databaseReference.push().setValue(contacts);
        progressBar.setVisibility(View.GONE);
        successmsg.setVisibility(View.VISIBLE);
        gohome.setVisibility(View.VISIBLE);
        layoutcontact.setVisibility(View.GONE);
        submit_li.setVisibility(View.GONE);
    }
}