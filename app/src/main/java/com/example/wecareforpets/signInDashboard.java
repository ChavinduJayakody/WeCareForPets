package com.example.wecareforpets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class signInDashboard extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_dashboard);

        dbHelper = new DBHelper();
        // Initialize EditText fields
        emailEditText = findViewById(R.id.emailAddress);
        passwordEditText = findViewById(R.id.password);
    }

    public void signInBtn(View view) {
        // This method seems redundant; you may want to handle sign-in here instead
    }

    public void signUpText(View view) {
        Intent intent = new Intent(this, signUpDashboard.class);
        startActivity(intent);
    }

    public void loginBtn(View view) {
        // Retrieve email and password entered by the user
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter both email and password.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if user is a Pet Owner
        dbHelper.checkPetOwner(email, password, new DBHelper.CheckCallback() {
            @Override
            public void onSuccess(boolean exists) {
                if (exists) {
                    Toast.makeText(signInDashboard.this, "Signed in successfully as Pet Owner.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(signInDashboard.this, mainMenu.class);
                    startActivity(intent);
                    finish(); // Finish the current activity
                } else {
                    // Check if user is a Caregiver
                    dbHelper.checkCaregiver(email, password, new DBHelper.CheckCallback() {
                        @Override
                        public void onSuccess(boolean exists) {
                            if (exists) {
                                Toast.makeText(signInDashboard.this, "Signed in successfully as Caregiver.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(signInDashboard.this, mainMenuCaregiver.class);
                                startActivity(intent);
                                finish(); // Finish the current activity
                            } else {
                                Toast.makeText(signInDashboard.this, "Invalid email or password.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
