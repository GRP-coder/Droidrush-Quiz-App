package com.example.droidrush_quiz_app.Student;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.example.droidrush_quiz_app.R;

import java.util.ArrayList;
import java.util.List;

public class student_quiz extends AppCompatActivity implements View.OnClickListener{

    TextView qno, question, count;
    Button op1, op2, op3, op4, submit, prevqn, nextqn;
    private List<QuestionModel> qList;
    int questionNo;
    public int marks;
    CountDownTimer countdown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_quiz);
        qno = findViewById(R.id.qnotv);
        question = findViewById(R.id.questiontv);
        count = findViewById(R.id.tcount);
        marks = 0;
        op1 = findViewById(R.id.option1);
        op2 = findViewById(R.id.option2);
        op3 = findViewById(R.id.option3);
        op4 = findViewById(R.id.option4);
        submit = findViewById(R.id.submitbt);
        prevqn = findViewById(R.id.prevqbt);
        nextqn = findViewById(R.id.nextqnbt);

        op1.setOnClickListener(this);
        op2.setOnClickListener(this);
        op3.setOnClickListener(this);
        op4.setOnClickListener(this);


        getQuestionList();

        nextqn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeQuestion();
            }
        });

        prevqn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prevQuestion();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), student_endof_quiz.class));
            }
        });
    }
    private void getQuestionList(){
        qList = new ArrayList<>();
        qList.add(new QuestionModel("Question1", "A", "B", "C", "D", 2));
        qList.add(new QuestionModel("Question1", "A", "B", "C", "D", 3));
        qList.add(new QuestionModel("Question1", "A", "B", "C", "D", 1));
        qList.add(new QuestionModel("Question1", "A", "B", "C", "D", 4));

        setQuestions();
    }


    private void setQuestions(){
        count.setText(String.valueOf(10));
        question.setText(qList.get(0).getQuestion());
        op1.setText(qList.get(0).getOp1());
        op2.setText(qList.get(0).getOp2());
        op3.setText(qList.get(0).getOp3());
        op4.setText(qList.get(0).getOp4());
        qno.setText(String.valueOf(1)+"/"+String.valueOf(qList.size()));
        questionNo = 0;
        startTimer();
    }

    private void startTimer(){
         countdown = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
              count.setText(String.valueOf(millisUntilFinished/1000));

            }

            @Override
            public void onFinish() {
                changeQuestion();
            }
        };

        countdown.start();

    }


    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        int selectedOption = 0;
        switch (v.getId()){
            case R.id.option1: selectedOption= 1;
            break;

            case R.id.option2: selectedOption= 2;
            break;

            case R.id.option3: selectedOption= 3;
            break;

            case R.id.option4: selectedOption= 4;
            break;

        }
        countdown.cancel();

        checkAnswer(selectedOption, v);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void checkAnswer(int selectOption, View v){
        if(selectOption == qList.get(questionNo).getCorrectAns()){
            //Right answer
            op1.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#fff")));
            op2.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#fff")));
            op3.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#fff")));
            op4.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#fff")));
            ((Button)v).setBackgroundTintList(ColorStateList.valueOf(Color.YELLOW));

            marks = marks+4;
        }

        else{
            //Wrong answer
        }

        //changeQuestion();
    }

    private void changeQuestion(){

        questionNo++;
        if(questionNo<qList.size()-1){
            loadAnimate( question , 0, 0);
            loadAnimate( op1 , 0, 1);
            loadAnimate( op2 , 0, 2);
            loadAnimate( op3 , 0, 3);
            loadAnimate( op4 , 0, 4);

            qno.setText(String.valueOf(questionNo+1)+"/"+String.valueOf(qList.size()));
            count.setText(String.valueOf(10));
            startTimer();
        }
        else{
            startActivity(new Intent(getApplicationContext(), student_endof_quiz.class));
        }


    }

    private void prevQuestion(){

        questionNo--;
        if(questionNo<qList.size()-1){
            loadAnimate( question , 0, 0);
            loadAnimate( op1 , 0, 1);
            loadAnimate( op2 , 0, 2);
            loadAnimate( op3 , 0, 3);
            loadAnimate( op4 , 0, 4);

            qno.setText(String.valueOf(questionNo+1)+"/"+String.valueOf(qList.size()));
            count.setText(String.valueOf(10));
            startTimer();
        }
        else{
            startActivity(new Intent(getApplicationContext(), student_endof_quiz.class));
        }


    }

    private void loadAnimate(final View view, final int value, final int viewNum){
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(300).setStartDelay(100).setInterpolator(new DecelerateInterpolator())
                .setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if(value == 0){
                    switch (viewNum){
                        case 0 : ((TextView)view).setText(qList.get(questionNo).getQuestion());break;
                        case 1 : ((Button)view).setText(qList.get(questionNo).getOp1());break;
                        case 2 : ((Button)view).setText(qList.get(questionNo).getOp2());break;
                        case 3 : ((Button)view).setText(qList.get(questionNo).getOp3());break;
                        case 4 : ((Button)view).setText(qList.get(questionNo).getOp4());break;

                    }
                    loadAnimate(view, 1, viewNum);
                }

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
}