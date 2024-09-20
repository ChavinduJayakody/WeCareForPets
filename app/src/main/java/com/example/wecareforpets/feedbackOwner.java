package com.example.wecareforpets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class feedbackOwner extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_owner);
    }

    public void submitCaregiverFeedback(View view) {
        Toast.makeText(this, "Feedback Sent Successfully", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, mainMenu.class);
        startActivity(intent);
        finish();
    }

    public void backBtn(View view) {
        Intent intent = new Intent(this, mainMenu.class);

        startActivity(intent);
    }


}