package com.example.droidrush_quiz_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.droidrush_quiz_app.Auth.StudentLogIn;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {
            startActivity(new Intent(MainActivity.this, StudentLogIn.class));
            finish();
        }
    },2000);
}
}