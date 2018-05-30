package com.u1.group2.unirideshare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.u1.group2.unirideshare.datamodels.DriverTrip;

public class EditTripDriver extends AppCompatActivity {

    DriverTrip driverTrip;
    TextView tripDetails;
    TextView rider1;
    TextView rider2;
    Button startTrip;
    Button cancelTrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_trip_driver);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        driverTrip = getIntent().getParcelableExtra("DRIVER_TRIP");
        tripDetails = findViewById(R.id.tripDetails);
        rider1 = findViewById(R.id.rider1);
        rider2 = findViewById(R.id.rider2);
        startTrip = findViewById(R.id.startTrip);
        cancelTrip = findViewById(R.id.cancelTrip);

        Window w = getWindow();
        w.setTitle(String.format("%s %s", driverTrip.getDay(), driverTrip.getToUni() ? "to Uni": "from Uni"));

        tripDetails.setText(String.format("2 Pickups %s %s Driver Fee $5", driverTrip.getTime(), driverTrip.getToUni() ? "to Uni": "from Uni"));
        rider1.setText(String.format("%s %s", driverTrip.getRiders().get(0).getFirstName(), driverTrip.getRiders().get(0).getHomeAddress()));
        rider2.setText(String.format("%s %s", driverTrip.getRiders().get(1).getFirstName(), driverTrip.getRiders().get(1).getHomeAddress()));

        cancelTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(EditTripDriver.this, Dashboard.class);
                intent.putExtra("CANCELLED_DRIVER_TRIP", driverTrip);
                startActivity(intent);
            }
        });

    }

}
