package com.example.ongoal;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    EditText editTextUsername, editTextEmail, editTextPassword;
    private FirebaseAuth mAuth;
    Dialog myDialog;
    CheckBox checkBoxTermsOfService;
    boolean booleanShowPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        booleanShowPassword = true;

        Objects.requireNonNull(getSupportActionBar()).hide(); //hide header with app name

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);

        mAuth = FirebaseAuth.getInstance();

        TextView tvLogin = findViewById(R.id.tvLogin);
        tvLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent logInIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                RegisterActivity.this.startActivity(logInIntent);
            }
        });

        Button buttonSignUp = findViewById(R.id.buttonSignUp);
        buttonSignUp.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        myDialog = new Dialog(this);
        checkBoxTermsOfService = findViewById(R.id.checkBox);
        checkBoxTermsOfService.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    showPopup(findViewById(android.R.id.content).getRootView());
                }
            }
        });

        onClickShowPassword();
    }

    private void registerUser() {
        String username = editTextUsername.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (username.isEmpty()) {
            editTextUsername.setError("Username is required");
            editTextUsername.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please enter a valid email");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        } else if (password.length() < 6) {
            editTextPassword.setError("Minimum length of password should be 6");
            editTextPassword.requestFocus();
            return;
        }

        if (!checkBoxTermsOfService.isChecked()) {
            checkBoxTermsOfService.setError("Accept Terms and Conditions");
            checkBoxTermsOfService.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    RegisterActivity.this.startActivity(intent);
                } else if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                    Toast.makeText(getApplicationContext(), "You are already registered", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void showPopup(View v) {
        myDialog.setContentView(R.layout.popup_terms_of_service);
        Button buttonDecline = myDialog.findViewById(R.id.buttonDecline);
        buttonDecline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxTermsOfService.setChecked(false);
                myDialog.dismiss();
            }
        });

        Button buttonAccept = myDialog.findViewById(R.id.buttonAccept);
        buttonAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxTermsOfService.setChecked(true);
                myDialog.dismiss();
            }
        });

        Objects.requireNonNull(getSupportActionBar()).hide(); //hide header with app name
        myDialog.show();
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
