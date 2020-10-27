package com.example.droidrush_quiz_app.Student;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.droidrush_quiz_app.Auth.StudentLogIn;
import com.example.droidrush_quiz_app.R;
import com.google.firebase.auth.FirebaseAuth;

public class student_control extends AppCompatActivity {
    Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_control);
        logout = findViewById(R.id.Student_control_Logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), StudentLogIn.class));
                finish();
            }
        });


    }
}