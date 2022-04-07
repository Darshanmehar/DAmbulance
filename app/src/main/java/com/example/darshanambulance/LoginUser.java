package com.example.darshanambulance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginUser extends AppCompatActivity implements View.OnClickListener {

    private EditText et_email,et_password;
    private Button login_btn,register_btn;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);
        et_email = (EditText) findViewById(R.id.user_email);
        et_password = (EditText) findViewById(R.id.user_password);
        login_btn = (Button) findViewById(R.id.login_btn);
        register_btn = (Button) findViewById(R.id.register_btn);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        login_btn.setOnClickListener(this);
        register_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.register_btn:
                 startActivity(new Intent(LoginUser.this,MainActivity.class));
                 break;
            case R.id.login_btn:
                userlogin();
                break;
        }
    }

    private void userlogin() {
        String email = et_email.getText().toString().trim();
        String password = et_password.getText().toString().trim();

        if(email.isEmpty()){
            et_email.setError("Please Enter Email Address");
            et_email.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            et_email.setError("Please Enter Valid Email Address");
            et_email.requestFocus();
            return;
        }

        if(password.isEmpty()){
            et_password.setError("Please Enter Password");
            et_password.requestFocus();
            return;
        }

        if(password.length() < 6){
            et_password.setError("Min Password lenght should be 6 characters");
            et_password.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                      // redirect to user dashboard
                      startActivity(new Intent(LoginUser.this,Dashboard.class));
                }else{
                    Toast.makeText(LoginUser.this,"Failed to login try gain", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }
}