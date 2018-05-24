package com.u1.group2.unirideshare.datamodels;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;

import com.u1.group2.unirideshare.R;

public class DriverSelector extends AppCompatActivity {

    CardView paulDriver;
    CardView johnDriver;
    CardView robertDriver;

    User paulDriverUser;
    User johnDriverUser;
    User robertDriverUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_selector);

        paulDriver = findViewById(R.id.paulDriver);
        johnDriver = findViewById(R.id.johnDriver);
        robertDriver = findViewById(R.id.robertDriver);

        //paulDriverUser = User();

    }
}
