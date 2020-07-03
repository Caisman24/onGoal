package com.example.ongoal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvSignUp = findViewById(R.id.tvSignUp);
        TextView tvSignUpBottom = findViewById(R.id.tvSignUpBottom);
        tvSignUp.setOnClickListener(onClickSignUp());
        tvSignUpBottom.setOnClickListener(onClickSignUp());
    }

    private View.OnClickListener onClickSignUp(){
        return new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, Register.class);
                MainActivity.this.startActivity(intent);
            }
        };
    }
}
