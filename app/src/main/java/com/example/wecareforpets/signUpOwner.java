package com.example.wecareforpets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signUpOwner extends AppCompatActivity {

    EditText regNameOwner, regEmailOwner, regPwOwner, regRePwOwner;
    Button btnReg;
    DBHelper dbHelper; // Firestore helper class

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_owner);

        regNameOwner = findViewById(R.id.fullNameOwner);
        regEmailOwner = findViewById(R.id.emailOwner);
        regPwOwner = findViewById(R.id.pwOwner);
        regRePwOwner = findViewById(R.id.RePwOwner);

        btnReg = findViewById(R.id.nextBtn1);
        dbHelper = new DBHelper(); // Firestore helper

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rUser, rEmail, rPwd, rRePwd;
                rUser = regNameOwner.getText().toString();
                rEmail = regEmailOwner.getText().toString();
                rPwd = regPwOwner.getText().toString();
                rRePwd = regRePwOwner.getText().toString();

                if (rUser.equals("") || rEmail.equals("") || rPwd.equals("") || rRePwd.equals("")) {
                    Toast.makeText(signUpOwner.this, "All fields required", Toast.LENGTH_LONG).show();
                } else {
                    if (rPwd.equals(rRePwd)) {
                        // Check if email already exists in Firestore
                        dbHelper.checkUsername(rEmail, new DBHelper.CheckCallback() {
                            @Override
                            public void onSuccess(boolean exists) {
                                if (exists) {
                                    // Email already exists
                                    Toast.makeText(signUpOwner.this, "Email Already Exists", Toast.LENGTH_LONG).show();
                                } else {
                                    // Proceed to the next sign-up step
                                    Intent intent = new Intent(signUpOwner.this, signUpOwner2.class);
                                    intent.putExtra("FULL_NAME", rUser);
                                    intent.putExtra("EMAIL", rEmail);
                                    intent.putExtra("PASSWORD", rPwd);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
                    } else {
                        Toast.makeText(signUpOwner.this, "Passwords do not match", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
