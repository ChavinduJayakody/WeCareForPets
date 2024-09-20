package com.example.wecareforpets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class signUpPet extends AppCompatActivity {
    EditText regNamePet, regAgePet;
    RadioGroup categoryPetRadio, sexPetRadio;
    Button btnReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_pet);

        regNamePet = findViewById(R.id.petName);
        regAgePet = findViewById(R.id.petAge);
        categoryPetRadio = findViewById(R.id.regCategoryPetRadio);
        sexPetRadio = findViewById(R.id.regSexPetRadio);
        btnReg = findViewById(R.id.nextBtn3);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedCategory = getSelectedRadioButtonValue(categoryPetRadio);
                String selectedSex = getSelectedRadioButtonValue(sexPetRadio);

                if (isAnyFieldEmpty(regNamePet, regAgePet)) {
                    Toast.makeText(signUpPet.this, "All Fields Required", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Retrieve data from the Intent (data from signUpOwner2)
                Intent intent = getIntent();
                String fullName = intent.getStringExtra("FULL_NAME");
                String email = intent.getStringExtra("EMAIL");
                String password = intent.getStringExtra("PASSWORD");
                String phone = intent.getStringExtra("PHONE");
                String address1 = intent.getStringExtra("ADDRESS1");
                String address2 = intent.getStringExtra("ADDRESS2");

                // Create a new Intent to start the next activity (signUpPet2)
                Intent nextIntent = new Intent(signUpPet.this, signUpPet2.class);

                // Pass data to the next activity
                nextIntent.putExtra("FULL_NAME", fullName);
                nextIntent.putExtra("EMAIL", email);
                nextIntent.putExtra("PASSWORD", password);
                nextIntent.putExtra("PHONE", phone);
                nextIntent.putExtra("ADDRESS1", address1);
                nextIntent.putExtra("ADDRESS2", address2);
                nextIntent.putExtra("PET_NAME", regNamePet.getText().toString().trim());
                nextIntent.putExtra("PET_AGE", regAgePet.getText().toString().trim());
                nextIntent.putExtra("PET_CATEGORY", selectedCategory);
                nextIntent.putExtra("PET_SEX", selectedSex);

                // Start the next activity
                startActivity(nextIntent);
            }
        });
    }

    private boolean isAnyFieldEmpty(EditText... editTexts) {
        for (EditText editText : editTexts) {
            if (editText.getText().toString().trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private String getSelectedRadioButtonValue(RadioGroup radioGroup) {
        int selectedId = radioGroup.getCheckedRadioButtonId();
        return (selectedId != -1) ? ((RadioButton) findViewById(selectedId)).getText().toString() : "";
    }
}
