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

public class signUpCaregiver extends AppCompatActivity {
    EditText careFullName, careEmail, carePhone, carePassword, careReEnterPassword;
    Button btnReg;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_caregiver);

        careFullName = findViewById(R.id.careFullName);
        careEmail = findViewById(R.id.careEmail);
        carePhone = findViewById(R.id.carePhone);
        carePassword = findViewById(R.id.carePassword);
        careReEnterPassword = findViewById(R.id.careRePassword);

        btnReg = findViewById(R.id.careNxt1);

        // Initialize Firestore
        db = FirebaseFirestore.getInstance();

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullName = careFullName.getText().toString().trim();
                String email = careEmail.getText().toString().trim();
                String phone = carePhone.getText().toString().trim();
                String password = carePassword.getText().toString().trim();
                String reEnterPassword = careReEnterPassword.getText().toString().trim();

                if (fullName.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty() || reEnterPassword.isEmpty()) {
                    showToast("All fields are required.");
                    return;
                }

                if (!password.equals(reEnterPassword)) {
                    showToast("Passwords do not match.");
                    return;
                }

                // Create a map to hold the caregiver's details
                Map<String, Object> caregiverDetails = new HashMap<>();
                caregiverDetails.put("fullName", fullName);
                caregiverDetails.put("email", email);
                caregiverDetails.put("phone", phone);
                caregiverDetails.put("password", password); // Note: Storing passwords in plaintext is not recommended

                // Save caregiver details to Firestore
                db.collection("Caregiver")
                        .document(email) // Use email as the document ID
                        .set(caregiverDetails)
                        .addOnSuccessListener(aVoid -> {
                            // Navigate to the next activity on successful registration
                            Intent intent = new Intent(signUpCaregiver.this, signUpCaregiver1.class);
                            intent.putExtra("FULL_NAME", fullName);
                            intent.putExtra("EMAIL", email);
                            intent.putExtra("PHONE", phone);
                            intent.putExtra("PASSWORD", password);
                            startActivity(intent);
                            finish(); // Optional: close this activity
                        })
                        .addOnFailureListener(e -> showToast("Error registering caregiver: " + e.getMessage()));
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
