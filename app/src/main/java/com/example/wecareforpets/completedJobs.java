package com.example.wecareforpets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class completedJobs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_jobs);
    }
    public void backBtn(View view) {
        Intent intent = new Intent(this, mainMenuCaregiver.class);

        startActivity(intent);
    }

}