package com.u1.group2.unirideshare;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.u1.group2.unirideshare.components.TripViewAdapter;
import com.u1.group2.unirideshare.datamodels.DriverTrip;
import com.u1.group2.unirideshare.datamodels.RiderTrip;
import com.u1.group2.unirideshare.datamodels.Trip;
import com.u1.group2.unirideshare.datamodels.User;

import java.util.ArrayList;
import java.util.HashMap;

import io.paperdb.Paper;


public class Dashboard extends AppCompatActivity {

    HashMap<Integer, DriverTrip> driverTrips;
    HashMap<Integer, RiderTrip> riderTrips;
    private RecyclerView tripsRecyclerView;
    private RecyclerView.Adapter tripsAdapter;
    private RecyclerView.LayoutManager tripsLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = findViewById(R.id.toolbar);
        this.tripsRecyclerView = (RecyclerView) findViewById(R.id.tripsView);
        this.tripsRecyclerView.setHasFixedSize(true);
        this.tripsLayoutManager = new LinearLayoutManager(this);
        this.tripsRecyclerView.setLayoutManager(this.tripsLayoutManager);

        setSupportActionBar(toolbar);

        this.driverTrips = Paper.book().read("driverTrips");
        this.riderTrips = Paper.book().read("riderTrips");

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            RiderTrip riderTrip = extras.getParcelable("RIDER_TRIP");
            DriverTrip driverTrip = extras.getParcelable("DRIVER_TRIP");
            if (driverTrip != null) {
                this.driverTrips.put(driverTrips.size(), driverTrip);
                Paper.book().write("driverTrips", driverTrips);
            }
            if (riderTrip != null) {
                this.riderTrips.put(riderTrips.size(), riderTrip);
                Paper.book().write("riderTrips", riderTrips);
            }
        }

        ArrayList<Trip> tripsData = new ArrayList<Trip>();
        tripsData.addAll(this.driverTrips.values());
        tripsData.addAll(this.riderTrips.values());

        this.tripsAdapter = new TripViewAdapter(tripsData);
        this.tripsRecyclerView.setAdapter(this.tripsAdapter);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, CreateTrip.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
