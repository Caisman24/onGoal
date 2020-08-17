package com.example.ongoal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    EditText editTextEmail, editTextPassword;
    Button buttonLogin;
    FirebaseAuth auth;
    TextView tvForgotPassword;
    boolean booleanShowPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        booleanShowPassword = true;

        Objects.requireNonNull(getSupportActionBar()).hide();

        TextView tvSignUp = findViewById(R.id.tvSignUp);
        tvSignUp.setOnClickListener(onClickSignUp());

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);

        auth = FirebaseAuth.getInstance();
        FirebaseAuth.AuthStateListener authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = auth.getCurrentUser();
                if (firebaseUser != null) {
                    Toast.makeText(LoginActivity.this, "You are logged in", Toast.LENGTH_SHORT).show();
                    Intent intentHome = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intentHome);
                } else {
                    Toast.makeText(LoginActivity.this, "Please enter your credentials", Toast.LENGTH_SHORT).show();
                }
            }
        };

        buttonLogin = findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        tvForgotPassword = findViewById(R.id.forgotPassword);
        tvForgotPassword.setOnClickListener(onClickForgotPassword());

        onClickShowPassword();
    }

    private void loginUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        // if email and password is not empty
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "Login Error, Please login Again", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intentHome = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intentHome);
                }
            }
        });

    }

    private View.OnClickListener onClickSignUp() {
        return new View.OnClickListener() {
            public void onClick(View v) {
                Intent registerUserIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerUserIntent);
            }
        };
    }

    private View.OnClickListener onClickForgotPassword() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgotPasswordIntent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                LoginActivity.this.startActivity(forgotPasswordIntent);
            }
        };
    }

    private void onClickShowPassword() {
        final ImageView imageShowPassword = findViewById(R.id.imageShowPassword);
        imageShowPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (booleanShowPassword) {
                    editTextPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    imageShowPassword.setImageDrawable(getResources().getDrawable(R.drawable.ic_round_eye_24));
                    editTextPassword.setSelection(editTextPassword.getText().length());
                    booleanShowPassword = false;
                } else {
                    editTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    imageShowPassword.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_visibility_off_24px));
                    editTextPassword.setSelection(editTextPassword.getText().length());
                    booleanShowPassword = true;
                }
            }
        });
    }
}
