package com.example.droidrush_quiz_app.Auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.droidrush_quiz_app.Admin.AdminControl;
import com.example.droidrush_quiz_app.R;
import com.example.droidrush_quiz_app.Teacher.teacher_control;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class TeacherLogIn extends AppCompatActivity {
    EditText emailt, passt;
    String email, password;
    Button login, stdlogin, admlogin;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_log_in);
        login= findViewById(R.id.loginb);
        emailt= findViewById(R.id.emailet);
        passt= findViewById(R.id.passet);
        stdlogin=findViewById(R.id.stdloginb);
        admlogin= findViewById(R.id.admloginb);

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
                            Toast.makeText(TeacherLogIn.this, "Email is empty", Toast.LENGTH_SHORT).show();
                        }
                        if (password.length()==0){
                            Toast.makeText(TeacherLogIn.this, "Password Cannot be empty", Toast.LENGTH_SHORT).show();
                        }

                        if (task.isSuccessful()) {
                            Toast.makeText(TeacherLogIn.this, "Login Succesful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), teacher_control.class));
                        }
                        else {
                            Toast.makeText(TeacherLogIn.this, "Authentication failed."+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

        });
        admlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AdminLogin.class));

            }
        });
        stdlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), StudentLogIn.class));

            }
        });
    }


}