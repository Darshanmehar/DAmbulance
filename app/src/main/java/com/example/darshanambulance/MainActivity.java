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
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button register,login;
    private EditText et_username, et_age,et_email,et_password;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register = (Button) findViewById(R.id.register_btn);
        login = (Button) findViewById(R.id.login_btn);
        et_username = (EditText) findViewById(R.id.user_name);
        et_age = (EditText) findViewById(R.id.user_age);
        et_email = (EditText) findViewById(R.id.user_email);
        et_password = (EditText) findViewById(R.id.user_password);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        register.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.register_btn:
                 registerUser();
                 break;
            case R.id.login_btn:
                Intent intent = new Intent(MainActivity.this, LoginUser.class);
                startActivity(intent);
                break;
        }
    }

    private void registerUser() {
        String username = et_username.getText().toString().trim();
        String age = et_age.getText().toString().trim();
        String email = et_email.getText().toString().trim();
        String password = et_password.getText().toString().trim();

        if(username.isEmpty()){
            et_username.setError("Please Enter Username");
            et_username.requestFocus();
            return;
        }
        if(username.isEmpty()){
            et_age.setError("Please Enter Your Age");
            et_age.requestFocus();
            return;
        }

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
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Users users = new Users(username,age,email);
                    FirebaseDatabase.getInstance().getReference("Users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(MainActivity.this,"User has been registered successfully !",Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                            }else{
                                Toast.makeText(MainActivity.this,"Failed to registered try gain", Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });
                }
            }
        });
    }
}