package com.example.ongoal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Objects.requireNonNull(getSupportActionBar()).hide(); //hide header with app name

        auth = FirebaseAuth.getInstance();

        CardView cardOnGoingGoal = findViewById(R.id.cardOnGoingGoal);
        // click listener

        CardView cardNewGoal = findViewById(R.id.cardNewGoal);
        // click listener

        CardView cardInfoApp = findViewById(R.id.cardInfoApp);
        cardInfoApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent infoApp = new Intent(MainActivity.this, AboutApp.class);
                startActivity(infoApp);
            }
        });

        CardView cardLogout = findViewById(R.id.cardLogout);
        cardLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent notLogged = new Intent(MainActivity.this, Login.class);
                startActivity(notLogged);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        final FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser == null) {
            Intent notLogged = new Intent(MainActivity.this, Login.class);
            startActivity(notLogged);
        }
    }
}
