package com.example.wecareforpets;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class signUpPet2 extends AppCompatActivity {
    EditText breedPet, colorPet, notesPet;
    Button btnReg;
    FirebaseFirestore db; // Firestore instance

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_pet2);

        breedPet = findViewById(R.id.breedPet);
        colorPet = findViewById(R.id.colorPet);
        notesPet = findViewById(R.id.notesPet);
        btnReg = findViewById(R.id.signUpBtnOwner);

        db = FirebaseFirestore.getInstance(); // Initialize Firestore

        // Retrieve data from the Intent (data from signUpPet)
        Intent intent = getIntent();
        String fullName = intent.getStringExtra("FULL_NAME");
        String email = intent.getStringExtra("EMAIL");
        String password = intent.getStringExtra("PASSWORD");
        String phone = intent.getStringExtra("PHONE");
        String address1 = intent.getStringExtra("ADDRESS1");
        String address2 = intent.getStringExtra("ADDRESS2");
        String petName = intent.getStringExtra("PET_NAME");
        String petAge = intent.getStringExtra("PET_AGE");
        String petCategory = intent.getStringExtra("PET_CATEGORY");
        String petSex = intent.getStringExtra("PET_SEX");

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get data from EditText fields
                String breed = breedPet.getText().toString().trim();
                String color = colorPet.getText().toString().trim();
                String notes = notesPet.getText().toString().trim();

                if (breed.isEmpty() || color.isEmpty() || notes.isEmpty()) {
                    Toast.makeText(signUpPet2.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Create a map to hold pet data
                Map<String, Object> petDetails = new HashMap<>();
                petDetails.put("fullName", fullName);
                petDetails.put("email", email);
                petDetails.put("phone", phone);
                petDetails.put("address1", address1);
                petDetails.put("address2", address2);
                petDetails.put("petName", petName);
                petDetails.put("petAge", petAge);
                petDetails.put("petCategory", petCategory);
                petDetails.put("petSex", petSex);
                petDetails.put("breed", breed);
                petDetails.put("color", color);
                petDetails.put("notes", notes);

                // Save pet details to Firestore
                db.collection("Pets") // Collection name can be adjusted
                        .document(email + "_" + petName) // Use a combination of email and pet name as a unique document ID
                        .set(petDetails)
                        .addOnSuccessListener(aVoid -> {
                            Toast.makeText(signUpPet2.this, "SignUp Successful", Toast.LENGTH_SHORT).show();
                            // Redirect to the dashboard or next activity
                            Intent dashboardIntent = new Intent(signUpPet2.this, mainMenu.class);
                            startActivity(dashboardIntent);
                            finish(); // Close the current activity
                        })
                        .addOnFailureListener(e -> Toast.makeText(signUpPet2.this, "SignUp Failed: " + e.getMessage(), Toast.LENGTH_SHORT).show());
            }
        });
    }
}
