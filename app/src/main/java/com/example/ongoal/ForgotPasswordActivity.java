package com.example.ongoal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Objects;

public class ForgotPasswordActivity extends AppCompatActivity {

    EditText editTextView1;
    Button buttonResetPassword, buttonTakeBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        Objects.requireNonNull(getSupportActionBar()).hide(); //hide header with app name

        editTextView1 = findViewById(R.id.editTextEmail);
        buttonResetPassword = findViewById(R.id.buttonResetPassword);
        buttonTakeBack = findViewById(R.id.buttonTakeBack);

        buttonTakeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ForgotPasswordActivity.this.finish();
            }
        });
    }
}