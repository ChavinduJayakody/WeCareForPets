package com.example.wecareforpets;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class mainMenu extends AppCompatActivity {


    CardView cardAddJobs;
    CardView cardYourJobs;
    CardView cardCustomerFeedback;
    CardView cardProfile;
    CardView cardSignOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        cardAddJobs = findViewById(R.id.cardAddJobs);
        cardYourJobs = findViewById(R.id.cardYourJobs);
        cardCustomerFeedback = findViewById(R.id.cardCustomerFeedbacks);
        cardProfile = findViewById(R.id.cardProfile);
        cardSignOut = findViewById(R.id.cardSignOut);

        cardAddJobs.setOnClickListener(new View.OnClickListener() {
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

        cardYourJobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Jobs();
            }
        });


        cardSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSignOutDialog();
            }
        });
        cardCustomerFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Feedback();
            }
        });



    }


    private void AddJobsActivity() {
        Intent intent = new Intent(this, addJobs.class);
        startActivity(intent);
    }
    private void ProfileActivity() {
        Intent intent = new Intent(this, profile.class);
        startActivity(intent);
    }
    private void Feedback() {
        Intent intent = new Intent(this, feedbackOwner.class);
        startActivity(intent);
    }
    private void Jobs() {
        Intent intent = new Intent(this, yourJobs.class);
        startActivity(intent);
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

    private void SignOutActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish(); // Optional: close the current activity when signing out
    }

}