package com.example.droidrush_quiz_app.Teacher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.droidrush_quiz_app.R;
import com.example.droidrush_quiz_app.Student.student_control;

public class teacher_control extends AppCompatActivity {
     TextView quizViewTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_control);
        quizViewTv = findViewById(R.id.viewquiz);

       quizViewTv.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(getApplicationContext(), teacher_view_quiz.class));
           }
       });
    }
}