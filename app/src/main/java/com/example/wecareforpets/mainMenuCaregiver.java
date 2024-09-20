package com.example.wecareforpets;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class mainMenuCaregiver extends AppCompatActivity {
    CardView cardViewJobs;
    CardView cardCompletedJobs;
    CardView cardCareFeedback;
    CardView cardProfile;
    CardView cardSignOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_caregiver);
        cardViewJobs = findViewById(R.id.cardViewJobs);
        cardCompletedJobs = findViewById(R.id.cardCompletedJobs);
        cardCareFeedback = findViewById(R.id.cardCareFeedbacks);
        cardProfile = findViewById(R.id.cardProfile);
        cardSignOut = findViewById(R.id.cardSignOut);

        cardViewJobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddJobsActivity();
            }
        });

        cardProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProfileActivity();
            }
        });

        cardCareFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FeedbackActivity();
            }
        });


        cardCompletedJobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                complete();
            }
        });

        cardSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSignOutDialog();
            }
        });


    }

    private void AddJobsActivity() {
        Intent intent = new Intent(this, viewJobsCaregiver.class);
        startActivity(intent);
    }

    private void FeedbackActivity() {
        Intent intent = new Intent(this, feedbackCaregiver.class);
        startActivity(intent);
    }

    private void ProfileActivity() {
        Intent intent = new Intent(this, profile.class);
        startActivity(intent);
    }
    private void complete() {
        Intent intent = new Intent(this, completedJobs.class);
        startActivity(intent);
    }
    private void SignOutActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    private void showSignOutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sign Out");
        builder.setMessage("Are you sure you want to sign out?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SignOutActivity();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}