package com.u1.group2.unirideshare;

import android.app.Application;

import com.u1.group2.unirideshare.datamodels.DriverTrip;
import com.u1.group2.unirideshare.datamodels.RiderTrip;

import java.util.ArrayList;
import java.util.HashMap;

import io.paperdb.Paper;

public class DefaultApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Paper.init(getApplicationContext());
        Paper.book().write("driverTrips", new ArrayList<DriverTrip>());
        Paper.book().write("riderTrips", new ArrayList<RiderTrip>());
    }
}
