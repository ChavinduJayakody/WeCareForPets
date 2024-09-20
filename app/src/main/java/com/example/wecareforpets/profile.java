package com.example.wecareforpets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class profile extends AppCompatActivity {

    DBHelper dbHelper;
    TextView fullNameTextView, emailTextView, phoneNumberTextView, address1TextView, address2TextView,
            petCategoryTextView, petNameTextView, petAgeTextView, petSexTextView, petBreedTextView;

    String userEmail; // You can set this via Intent or Firebase Auth if available

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

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

        // Initialize DBHelper (which contains Firestore methods)
        dbHelper = new DBHelper();

        // Retrieve user email passed from a previous activity (or from FirebaseAuth)
        Intent intent = getIntent();
        userEmail = intent.getStringExtra("EMAIL");

        // Fetch user details from Firestore
        fetchUserProfile(userEmail);
    }

    private void fetchUserProfile(String email) {
        if (email == null || email.isEmpty()) {
            Toast.makeText(this, "No user email provided", Toast.LENGTH_LONG).show();
            return;
        }

        // Fetch the user's data from Firestore using DBHelper
        dbHelper.getPetOwnerDetails(email, new DBHelper.DataCallback() {
            @Override
            public void onDataReceived(Map<String, Object> data) {
                if (data != null) {
                    // Populate the TextViews with the data received from Firestore
                    fullNameTextView.setText((String) data.get("fullName"));
                    emailTextView.setText((String) data.get("email"));
                    phoneNumberTextView.setText((String) data.get("phoneNumber"));
                    address1TextView.setText((String) data.get("address1"));
                    address2TextView.setText((String) data.get("address2"));
                    petCategoryTextView.setText((String) data.get("petCategory"));
                    petNameTextView.setText((String) data.get("petName"));
                    petAgeTextView.setText((String) data.get("petAge"));
                    petSexTextView.setText((String) data.get("petSex"));
                    petBreedTextView.setText((String) data.get("petBreed"));
                } else {
                    Toast.makeText(profile.this, "Failed to retrieve profile", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void backBtn1() {
        Intent intent = new Intent(this, mainMenu.class);
        startActivity(intent);
    }
}
