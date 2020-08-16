package com.example.ongoal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Objects;

public class GetStartedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);

        Objects.requireNonNull(getSupportActionBar()).hide(); //hide header with app name

        //Button buttonSignFacebook = findViewById(R.id.buttonSignFacebook);
        Button buttonRegister = findViewById(R.id.buttonRegister);
        TextView tvLogin = findViewById(R.id.tvLogin);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerUser = new Intent(GetStartedActivity.this, RegisterActivity.class);
                startActivity(registerUser);
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginrUser = new Intent(GetStartedActivity.this, LoginActivity.class);
                startActivity(loginrUser);
            }
        });
    }
}