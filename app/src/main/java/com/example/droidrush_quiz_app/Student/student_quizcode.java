package com.example.droidrush_quiz_app.Student;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.droidrush_quiz_app.Auth.StudentLogIn;
import com.example.droidrush_quiz_app.R;
import com.google.firebase.auth.FirebaseAuth;

public class student_quizcode extends AppCompatActivity {
    Button  startquiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_quizcode);

        startquiz = findViewById(R.id.startquizbt);

        startquiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), student_quiz.class));
            }
        });
    }
}