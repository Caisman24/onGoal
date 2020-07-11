package com.example.ongoal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Objects;

public class AboutAppActivity extends AppCompatActivity {

    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);
        myDialog = new Dialog(this);

        Objects.requireNonNull(getSupportActionBar()).hide(); //hide header with app name
    }

    public void ShowPopup(View v) {
        myDialog.setContentView(R.layout.popup_profile);
        Button buttonClosePopup = myDialog.findViewById(R.id.buttonClosePopup);
        buttonClosePopup.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        Objects.requireNonNull(myDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }
}