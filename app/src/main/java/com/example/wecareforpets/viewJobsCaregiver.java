package com.example.wecareforpets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class viewJobsCaregiver extends AppCompatActivity {

    private TextView textViewJob1;
    private TextView textViewJob2;
    private TextView textViewJob3;
    private View job1Layout;
    private View job2Layout;
    private View job3Layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_jobs_caregiver);

        textViewJob1 = findViewById(R.id.textViewJob1);
        textViewJob2 = findViewById(R.id.textViewJob2);
        textViewJob3 = findViewById(R.id.textViewJob3);
        job1Layout = findViewById(R.id.job1_layout);
        job2Layout = findViewById(R.id.job2_layout);
        job3Layout = findViewById(R.id.job3_layout);
    }
    public void backBtn(View view) {
        Intent intent = new Intent(this, mainMenuCaregiver.class);

        startActivity(intent);
    }
    public void enroll(View view) {
        Toast.makeText(this, "Job Enrolled Successfully", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, mainMenu.class);
        startActivity(intent);
        finish();
    }

    public void searchJobs(View view) {
        EditText editTextSearch = findViewById(R.id.editTextSearch);
        String query = editTextSearch.getText().toString().toLowerCase();

        // Hardcoded job entries
        String job1Title = "Looking for Caregiver for Lion Shepherd";
        String job1Days = "Days: 5 Days";
        String job1ID = "Job ID : J01";

        String job2Title = "Looking for Caregiver for Boxer";
        String job2Days = "Days: 3 Days";
        String job2ID = "Job ID : J02";

        String job3Title = "Looking for Caregiver for American Bully";
        String job3Days = "Days: 1 Days";
        String job3ID = "Job ID : J03";

        boolean job1Matches = job1Title.toLowerCase().contains(query) || job1Days.toLowerCase().contains(query) || job1ID.toLowerCase().contains(query);
        boolean job2Matches = job2Title.toLowerCase().contains(query) || job2Days.toLowerCase().contains(query) || job2ID.toLowerCase().contains(query);
        boolean job3Matches = job3Title.toLowerCase().contains(query) || job3Days.toLowerCase().contains(query) || job3ID.toLowerCase().contains(query);

        // Set initial visibility state
        job1Layout.setVisibility(View.VISIBLE);
        job2Layout.setVisibility(View.VISIBLE);
        job3Layout.setVisibility(View.VISIBLE);

        // Update visibility based on search results
        if (!job1Matches) {
            job1Layout.setVisibility(View.GONE);
        }
        if (!job2Matches) {
            job2Layout.setVisibility(View.GONE);
        }
        if (!job3Matches) {
            job3Layout.setVisibility(View.GONE);
        }
    }
}
