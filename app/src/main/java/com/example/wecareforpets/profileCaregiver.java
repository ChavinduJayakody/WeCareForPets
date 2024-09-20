package com.example.wecareforpets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class profileCaregiver extends AppCompatActivity {
    DBHelper dbHelper;
    TextView fullNameTextView, emailTextView, phoneNumberTextView, address1TextView, address2TextView,
            petCategoryTextView, petNameTextView, petAgeTextView, petSexTextView, petBreedTextView;

    String userEmail; // This should be initialized with the logged-in caregiver's email

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_caregiver);

        // Initialize DBHelper
        dbHelper = new DBHelper();

        // Initialize TextViews
        fullNameTextView = findViewById(R.id.fullNameTextView);
        emailTextView = findViewById(R.id.emailTextView);
        phoneNumberTextView = findViewById(R.id.phoneNumberTextView);
        address1TextView = findViewById(R.id.address1TextView);
        address2TextView = findViewById(R.id.address2TextView);
        petCategoryTextView = findViewById(R.id.petCategoryTextView);
        petNameTextView = findViewById(R.id.petNameTextView);
        petAgeTextView = findViewById(R.id.petAgeTextView);
        petSexTextView = findViewById(R.id.petSexTextView);
        petBreedTextView = findViewById(R.id.petBreedTextView);

        // Get user email from the Intent or SharedPreferences
        // userEmail = getIntent().getStringExtra("USER_EMAIL"); // Uncomment if passing email through Intent
        // Alternatively, you can retrieve it from SharedPreferences or a static variable

        // Fetch caregiver details from Firestore
        getCaregiverDetails(userEmail);
    }

    private void getCaregiverDetails(String email) {
        dbHelper.getCaregiverDetails(email, new DBHelper.DataCallback() {
            @Override
            public void onDataReceived(Map<String, Object> data) {
                if (data != null) {
                    // Update UI with caregiver details
                    fullNameTextView.setText((String) data.get("fullName"));
                    emailTextView.setText((String) data.get("email"));
                    phoneNumberTextView.setText((String) data.get("phoneNumber"));
                    address1TextView.setText((String) data.get("address1"));
                    address2TextView.setText((String) data.get("address2"));
                    // Assuming these details are relevant to caregivers; adjust if necessary
                } else {
                    Toast.makeText(profileCaregiver.this, "No details found.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void backBtn1() {
        Intent intent = new Intent(this, mainMenuCaregiver.class);
        startActivity(intent);
    }
}
