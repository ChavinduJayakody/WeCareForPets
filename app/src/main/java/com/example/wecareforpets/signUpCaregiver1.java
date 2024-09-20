package com.example.wecareforpets;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class signUpCaregiver1 extends AppCompatActivity {
    EditText careDateOfBirth, careAddress1, careAddress2, careAddress3;
    Button btnReg;
    FirebaseFirestore db; // Firestore instance

    String FullName, Email, Phone, Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_caregiver1);

        careDateOfBirth = findViewById(R.id.careDateOfBirth);
        careAddress1 = findViewById(R.id.careAddress1);
        careAddress2 = findViewById(R.id.careAddress2);
        careAddress3 = findViewById(R.id.careAddress3);
        btnReg = findViewById(R.id.careSignUpBtn);

        db = FirebaseFirestore.getInstance(); // Initialize Firestore

        // Retrieve data from the Intent
        Intent intent = getIntent();
        if (intent != null) {
            FullName = intent.getStringExtra("FULL_NAME");
            Email = intent.getStringExtra("EMAIL");
            Phone = intent.getStringExtra("PHONE");
            Password = intent.getStringExtra("PASSWORD");
        }

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dateOfBirth = careDateOfBirth.getText().toString();
                String address1 = careAddress1.getText().toString();
                String address2 = careAddress2.getText().toString();
                String address3 = careAddress3.getText().toString();

                // Create a map to hold the caregiver data
                Map<String, Object> caregiverDetails = new HashMap<>();
                caregiverDetails.put("fullName", FullName);
                caregiverDetails.put("email", Email);
                caregiverDetails.put("phone", Phone);
                caregiverDetails.put("password", Password); // Store the password only if necessary (not recommended)
                caregiverDetails.put("dateOfBirth", dateOfBirth);
                caregiverDetails.put("address1", address1);
                caregiverDetails.put("address2", address2);
                caregiverDetails.put("address3", address3);

                // Save caregiver details to Firestore
                db.collection("Caregiver")
                        .document(Email) // Use email as the document ID
                        .set(caregiverDetails)
                        .addOnSuccessListener(aVoid -> {
                            showToast("Signed Up Successfully");
                            Intent signInIntent = new Intent(signUpCaregiver1.this, mainMenuCaregiver.class);
                            startActivity(signInIntent);
                            finish(); // Close this activity
                        })
                        .addOnFailureListener(e -> showToast("Sign Up Failed: " + e.getMessage()));
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void displayDatePickerDialog(View view) {
        DialogFragment datePickerFragment = new CustomDatePickerFragment();
        datePickerFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public static class CustomDatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        EditText dateOfBirthEditText;

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int initialYear = calendar.get(Calendar.YEAR);
            int initialMonth = calendar.get(Calendar.MONTH);
            int initialDay = calendar.get(Calendar.DAY_OF_MONTH);

            View rootView = getActivity().getWindow().getDecorView().findViewById(android.R.id.content);
            dateOfBirthEditText = rootView.findViewById(R.id.careDateOfBirth);

            DatePickerDialog datePickerDialog = new DatePickerDialog(requireActivity(), this, initialYear, initialMonth, initialDay);
            datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
            return datePickerDialog;
        }

        public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
            String selectedDate = String.format(Locale.getDefault(), "%04d-%02d-%02d", selectedYear, selectedMonth + 1, selectedDay);
            dateOfBirthEditText.setText(selectedDate);
        }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            // Update the EditText when the fragment is re-created
            if (dateOfBirthEditText != null && !dateOfBirthEditText.getText().toString().isEmpty()) {
                String[] parts = dateOfBirthEditText.getText().toString().split("-");
                int year = Integer.parseInt(parts[0]);
                int month = Integer.parseInt(parts[1]) - 1; // Month is 0-based
                int day = Integer.parseInt(parts[2]);
                ((DatePickerDialog) requireDialog()).updateDate(year, month, day);
            }
        }
    }
}
