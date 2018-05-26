package com.u1.group2.unirideshare;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.u1.group2.unirideshare.R;
import com.u1.group2.unirideshare.datamodels.RiderTrip;
import com.u1.group2.unirideshare.datamodels.User;

public class DriverSelector extends AppCompatActivity {

    CardView paulDriver;
    CardView johnDriver;
    CardView robertDriver;

    User paulDriverUser;
    User johnDriverUser;
    User robertDriverUser;
    RiderTrip trip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_selector);

        this.paulDriver = findViewById(R.id.paulDriver);
        this.johnDriver = findViewById(R.id.johnDriver);
        this.robertDriver = findViewById(R.id.robertDriver);

        this.paulDriverUser = new User("Paul", "Paul", "paul.paul@gmail.com", "45 Windsor Rd Kelvin Grove 4059", "UQ", 19, 5, "254TFG");
        this.johnDriverUser = new User("John", "John", "john.john@gmail.com", "389 Newmarket Rd Newmarket 4051", "UQ", 20, 3, "324AGD");
        this.robertDriverUser = new User("Robert", "Robert", "robert.robert@gmail.com", "50 Ashgrove Ave Ashgrove 4060", "UQ", 21, 4, "567TYA");
        Bundle data = getIntent().getExtras();

        assert data != null;
        this.trip = data.getParcelable("RIDER_TRIP");

        this.paulDriver.setOnClickListener(createOnClickListener(paulDriverUser));
        this.robertDriver.setOnClickListener(createOnClickListener(robertDriverUser));
        this.johnDriver.setOnClickListener(createOnClickListener(johnDriverUser));

    }

    protected AlertDialog generateAreYouSurePopup(final User driverData) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(DriverSelector.this);
        builder1.setMessage("Are you sure you want to select this driver.");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        completeCreateTrip(driverData);
                    }
                });
        builder1.setNegativeButton(
                "NO",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                       dialog.cancel();
                    }
                });
        return builder1.create();
    }

    private View.OnClickListener createOnClickListener(final User driverData) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateAreYouSurePopup(driverData).show();
            }
        };
    }

    private void completeCreateTrip(User driverData) {
        this.trip.setDriver(driverData);
        Intent intent = new Intent(DriverSelector.this, Dashboard.class);
        intent.putExtra("RIDER_TRIP", this.trip);
        startActivity(intent);
    }
}
