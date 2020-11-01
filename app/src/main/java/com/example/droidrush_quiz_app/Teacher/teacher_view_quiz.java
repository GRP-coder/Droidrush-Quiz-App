package com.example.droidrush_quiz_app.Teacher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.example.droidrush_quiz_app.R;
import com.example.droidrush_quiz_app.Teacher.teacher_view_quiz_repeat;

import java.util.ArrayList;
import java.util.List;

public class teacher_view_quiz extends AppCompatActivity {
    private GridView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_view_quiz);

        list = findViewById(R.id.quizlist);
        List<String> qlist =new ArrayList<>();
        qlist.add("Quiz 1 : Quizcode 1");
        qlist.add("Quiz 2 : Quizcode 2");
        qlist.add("Quiz 3 : Quizcode 3");
        qlist.add("Quiz 4 : Quizcode 4");
        qlist.add("Quiz 5 : Quizcode 5");
        teacher_view_quiz_repeat reapQuestion = new teacher_view_quiz_repeat(qlist);
     list.setAdapter(reapQuestion);
    }
}