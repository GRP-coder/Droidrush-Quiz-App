package com.example.droidrush_quiz_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AdminLoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    EditText emailt, passt;
    String email, password;
    Button login;

    Button stdentlogb, teacherlogb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        login= findViewById(R.id.loginb);
        emailt= findViewById(R.id.emailet);
        passt= findViewById(R.id.passet);

        mAuth = FirebaseAuth.getInstance();

            login.setOnClickListener(new View.OnClickListener() {
               @Override
                public void onClick(View v) {
                    email= emailt.getText().toString().trim();
                    password = passt.getText().toString().trim();

                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                   if (email.length()==0){
                                       Toast.makeText(AdminLoginActivity.this, "Email is empty", Toast.LENGTH_SHORT).show();
                                   }
                                    if (password.length()==0){
                                        Toast.makeText(AdminLoginActivity.this, "Password Cannot be empty", Toast.LENGTH_SHORT).show();
                                    }

                                    if (task.isSuccessful()) {
                                        Toast.makeText(AdminLoginActivity.this, "Login Succesful", Toast.LENGTH_SHORT).show();
                                       startActivity(new Intent(getApplicationContext(),AdminControl.class));
                                    }
                                    else {
                                        Toast.makeText(AdminLoginActivity.this, "Authentication failed."+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                    }

            });
        stdentlogb = findViewById(R.id.studentlb);
        teacherlogb = findViewById(R.id.tchrlogb);
        teacherlogb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),TeacherLogIn.class));

            }
        });
        stdentlogb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),StudentLogIn.class));

            }
        });
    }

}



