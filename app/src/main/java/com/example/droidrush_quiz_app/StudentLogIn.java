package com.example.droidrush_quiz_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class StudentLogIn extends AppCompatActivity {
        Button admlogin, tchrlogin, login;
        EditText emailt, passt;
        String email, password;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_login);
        admlogin= findViewById(R.id.button3);
        tchrlogin= findViewById(R.id.button2);
        login= findViewById(R.id.btnLogin);
        emailt= findViewById(R.id.etName);
        passt= findViewById(R.id.etPassword);

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
                            Toast.makeText(StudentLogIn.this, "Email is empty", Toast.LENGTH_SHORT).show();
                        }
                        if (password.length()==0){
                            Toast.makeText(StudentLogIn.this, "Password Cannot be empty", Toast.LENGTH_SHORT).show();
                        }

                        if (task.isSuccessful()) {
                            Toast.makeText(StudentLogIn.this, "Login Succesful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Quiz.class));
                        }
                        else {
                            Toast.makeText(StudentLogIn.this, "Authentication failed."+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

        });


        admlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AdminLoginActivity.class));

            }
        });
        tchrlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),TeacherLogIn.class));

            }
        });
    }


}