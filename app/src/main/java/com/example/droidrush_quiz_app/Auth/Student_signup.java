package com.example.droidrush_quiz_app.Auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.droidrush_quiz_app.R;
import com.example.droidrush_quiz_app.Auth.TeacherLogIn;
import com.example.droidrush_quiz_app.Student.student_control;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Student_signup extends AppCompatActivity {

    private EditText emailt_signup, passt_signup, passcheck_std, namet_std, regnot_std;
    TextView loginbt, already_reg;
    Button signup_std;
    private String email, password, passch, name, reg_no, UserID;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private static final String TAG = "Student_signup";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_sign_up);
        emailt_signup = findViewById(R.id.et_emailid);
        namet_std = findViewById(R.id.etName);
        passt_signup = findViewById(R.id.etPassword);
        passcheck_std = findViewById(R.id.etPascheck);
        regnot_std = findViewById(R.id.regno);
        loginbt = findViewById(R.id.et_stlogin);
        already_reg = findViewById(R.id.etRegistration);
        signup_std = findViewById(R.id.btnSignup);
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(), student_control.class));
            finish();
        }


        signup_std.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = emailt_signup.getText().toString().trim();
                password = passt_signup.getText().toString().trim();
                passch = passcheck_std.getText().toString().trim();
                name = namet_std.getText().toString();
                reg_no = regnot_std.getText().toString();


                if (!(password.equals(passch))) {
                    Toast.makeText(Student_signup.this, "Password and Retype password dose not match", Toast.LENGTH_SHORT).show();
                }

                if (email.length() == 0) {
                    Toast.makeText(Student_signup.this, "Email is empty", Toast.LENGTH_SHORT).show();
                    emailt_signup.requestFocus();
                    return;
                }
                if (password.length() == 0) {
                    Toast.makeText(Student_signup.this, "Password Cannot be empty", Toast.LENGTH_SHORT).show();
                    passt_signup.requestFocus();
                    return;
                }
                if (passch.length() == 0) {
                    Toast.makeText(Student_signup.this, "Password Cannot be empty", Toast.LENGTH_SHORT).show();
                    passcheck_std.requestFocus();
                    return;
                }


                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "createUserWithEmail:success");
                                    Map<String, Object> userdata = new HashMap<>();
                                    userdata.put("Reg.No", reg_no);
                                    userdata.put("Email", email);
                                    userdata.put("Name", name);
                                    userdata.put("Key", "0");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    UserID = user.getUid();
                                    DocumentReference df = db.collection("users").document(UserID);

                                    df.set(userdata).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                        Log.d(TAG, "Written Succesfully");
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.w(TAG, "Unsuccesful");
                                        }
                                    });
                                   // Toast.makeText(Student_signup.this, "Signup Succesful", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), student_control.class));
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(Student_signup.this, "Creating account failed." + task.getException(), Toast.LENGTH_SHORT).show();
                                }
                            }

                        });



            }


        });
        loginbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), StudentLogIn.class));
            }
        });

        already_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), StudentLogIn.class));
            }
        });


    }
}



