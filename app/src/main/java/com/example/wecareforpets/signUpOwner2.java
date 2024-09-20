package com.example.wecareforpets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signUpOwner2 extends AppCompatActivity {
    EditText regPhoneOwner,regAddress1Owner,regAddress2Owner;
    Button btnReg;
    DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_owner2);

        regPhoneOwner = findViewById(R.id.phoneOwner);
        regAddress1Owner = findViewById(R.id.address1Owner);
        regAddress2Owner = findViewById(R.id.address2Owner);
        btnReg = findViewById(R.id.nextBtn2);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rPhone, rAdd1, rAdd2;
                rPhone = regPhoneOwner.getText().toString().trim();
                rAdd1 = regAddress1Owner.getText().toString().trim();
                rAdd2 = regAddress2Owner.getText().toString().trim();

                // Check if any of the fields are empty
                if (rPhone.isEmpty() || rAdd1.isEmpty() || rAdd2.isEmpty()) {
                    // Display a Toast if any field is empty
                    Toast.makeText(signUpOwner2.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Retrieve data from the Intent
                Intent intent = getIntent();
                String fullName = intent.getStringExtra("FULL_NAME");
                String email = intent.getStringExtra("EMAIL");
                String password = intent.getStringExtra("PASSWORD");

                // Create a new Intent to start the next activity
                Intent nextIntent = new Intent(signUpOwner2.this, signUpPet.class);

                // Pass the data to the next activity
                nextIntent.putExtra("FULL_NAME", fullName);
                nextIntent.putExtra("EMAIL", email);
                nextIntent.putExtra("PASSWORD", password);
                nextIntent.putExtra("PHONE", rPhone);
                nextIntent.putExtra("ADDRESS1", rAdd1);
                nextIntent.putExtra("ADDRESS2", rAdd2);

                // Start the next activity
                startActivity(nextIntent);
            }
        });
    }
}