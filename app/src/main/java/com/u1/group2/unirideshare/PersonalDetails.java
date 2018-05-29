package com.u1.group2.unirideshare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;

public class PersonalDetails extends AppCompatActivity {

    private String email;
    TextView firstName;
    TextView lastName;
    TextView phoneNumber;
    Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);

        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        phoneNumber = findViewById(R.id.phone);
        continueButton = findViewById(R.id.continueButton);

        Bundle extras = getIntent().getExtras();


        email = extras.getString("EMAIL");

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                continueSignup();
            }
        });


    }

    private void continueSignup() {
        Intent intent = new Intent(PersonalDetails.this, AddressDetails.class);
        intent.putExtra("EMAIL", email);
        intent.putExtra("FIRST_NAME", firstName.getText().toString());
        intent.putExtra("LAST_NAME", lastName.getText().toString());
        intent.putExtra("PHONE_NUMBER", phoneNumber.getText().toString());
        startActivity(intent);
    }
}
