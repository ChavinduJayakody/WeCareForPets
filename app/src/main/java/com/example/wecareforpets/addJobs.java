package com.example.wecareforpets;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class addJobs extends AppCompatActivity {

    private EditText careDays;
    private TextView totalPriceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_jobs);

        careDays = findViewById(R.id.careDays);
        totalPriceTextView = findViewById(R.id.totalPriceTextView);

        careDays.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                calculateTotalPrice();
            }
        });
    }

    private void backBtn(View view) {
        Intent intent = new Intent(this, mainMenu.class);
        startActivity(intent);
    }
    public void proceedClicked(View view) {
        Toast.makeText(this, "Job Added Successfully", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, mainMenu.class);
        startActivity(intent);
        finish();
    }
    private void calculateTotalPrice() {
        String daysString = careDays.getText().toString().trim();

        if (!daysString.isEmpty()) {
            int days = Integer.parseInt(daysString);

            double pricePerDay = 2000.00;
            double totalPrice = days * pricePerDay;
            totalPriceTextView.setText(String.format("Total Price: Rs.%.2f", totalPrice));
        } else {
            totalPriceTextView.setText("Total Price: Rs.0.00");
        }
    }
}