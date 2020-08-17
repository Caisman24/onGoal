package com.example.ongoal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class CreateGoalActivity extends AppCompatActivity {

    private EditText editTextView1, editTextView2;
    private Button buttonCreateGoal;
    private Goal currentGoal;
    private GoalHandler goalHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_goal);

        Objects.requireNonNull(getSupportActionBar()).hide(); //hide header with app name

        editTextView1 = findViewById(R.id.editCreateGoal);
        editTextView2 = findViewById(R.id.editNumberofDays);
        buttonCreateGoal = findViewById(R.id.buttonCreateGoal);

        buttonCreateGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth auth = FirebaseAuth.getInstance();
                FirebaseUser user = auth.getCurrentUser();

                if (user != null) {
                    if (TextUtils.isDigitsOnly(editTextView2.getText().toString())) {
                        currentGoal = new Goal(Integer.parseInt(editTextView2.getText().toString()), editTextView1.getText().toString(), user.getUid());
                        goalHandler = new GoalHandler(currentGoal);
                        goalHandler.addGoal();
                        Intent finishActivity = new Intent(CreateGoalActivity.this, MainActivity.class);
                        startActivity(finishActivity);
                    } else
                        editTextView2.setError("Please enter only digits");
                }
            }
        });
    }
}