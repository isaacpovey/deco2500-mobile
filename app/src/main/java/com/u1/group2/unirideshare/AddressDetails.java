package com.u1.group2.unirideshare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;
import com.u1.group2.unirideshare.datamodels.User;

import io.paperdb.Paper;

public class AddressDetails extends AppCompatActivity {

    private FirebaseUser user;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    TextView homeAddress;
    TextView uni;
    Button completeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adress_details);
        homeAddress = findViewById(R.id.homeAddress);
        uni = findViewById(R.id.uni);
        completeButton = findViewById(R.id.completeButton);

        Bundle extras = getIntent().getExtras();

        user = extras.getParcelable("USER");
        email = extras.getString("EMAIL");
        firstName = extras.getString("FIRST_NAME");
        lastName = extras.getString("LAST_NAME");
        phoneNumber = extras.getString("PHONE_NUMBER");



        completeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                continueSignup();
            }
        });


    }

    private void continueSignup() {
        Intent intent = new Intent(AddressDetails.this, Dashboard.class);
        Paper.book().write("FIREBASE_USER", user);
        Paper.book().write("USER", new User(firstName, lastName, email, homeAddress.getText().toString(), uni.getText().toString(), 20, 5, ""));
        startActivity(intent);
    }
}
