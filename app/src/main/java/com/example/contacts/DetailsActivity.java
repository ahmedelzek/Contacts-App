package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    String name, phone, description;
    TextView nameTextView, phoneTextView, descriptionTextView;

    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        getInfo();

        defineViews();

        setInfo();
        backButton.setOnClickListener(view -> finish());

    }

    void defineViews() {
        nameTextView = findViewById(R.id.nameTextView);
        phoneTextView = findViewById(R.id.phoneTextView);
        descriptionTextView = findViewById(R.id.descriptionTextView);
        backButton = findViewById(R.id.backButton);
    }

    void getInfo() {
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        phone = intent.getStringExtra("phone");
        description = intent.getStringExtra("description");
    }

    void setInfo() {
        nameTextView.setText(name);
        phoneTextView.setText(phone);
        if (description != null && !description.isEmpty()) {
            descriptionTextView.setText(description);
        } else {
            descriptionTextView.setVisibility(View.GONE);
        }
    }
}