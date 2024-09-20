package com.example.wecareforpets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class signUpDashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_dashboard);
    }

    public void signUpOwnerBtn(View view) {
        Intent intent = new Intent(this, signUpOwner.class);
        startActivity(intent);
    }

    public void signUpCaregiverBtn(View view) {
        Intent intent = new Intent(this, signUpCaregiver.class);
        startActivity(intent);
    }

}