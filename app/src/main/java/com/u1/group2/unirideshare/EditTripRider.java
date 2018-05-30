package com.u1.group2.unirideshare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.u1.group2.unirideshare.datamodels.DriverTrip;
import com.u1.group2.unirideshare.datamodels.RiderTrip;

public class EditTripRider extends AppCompatActivity {

    RiderTrip riderTrip;
    TextView tripDetails;
    TextView driverDetails;
    Button cancelTrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_trip_rider);
        riderTrip = getIntent().getParcelableExtra("RIDER_TRIP");
        tripDetails = findViewById(R.id.tripDetails);
        driverDetails = findViewById(R.id.driverDetails);
        cancelTrip = findViewById(R.id.cancelTrip);

        Window w = getWindow();
        w.setTitle(String.format("%s %s", riderTrip.getDay(), riderTrip.getToUni() ? "to Uni": "from Uni"));

        tripDetails.setText(String.format("%s %s Passenger Fee $5", riderTrip.getTime(), riderTrip.getToUni() ? "to Uni": "from Uni"));
        driverDetails.setText(String.format("Driver: %s %s", riderTrip.getDriver().getFirstName(), riderTrip.getDriver().getLicencePlate()));



        cancelTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(EditTripRider.this, Dashboard.class);
                intent.putExtra("CANCELLED_RIDER_TRIP", riderTrip);
                startActivity(intent);
            }
        });

    }
}
