package com.example.wecareforpets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void signInBtn(View view) {
        Intent intent = new Intent(this, signInDashboard.class);
        startActivity(intent);
    }
    public void signUpBtn(View view) {
        Intent intent = new Intent(this, signUpDashboard.class);
        startActivity(intent);
    }



}