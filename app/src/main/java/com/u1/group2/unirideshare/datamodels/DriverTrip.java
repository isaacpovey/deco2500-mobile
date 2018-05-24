package com.u1.group2.unirideshare.datamodels;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Arrays;

public class DriverTrip extends Trip {

    private ArrayList<User> riders;


    public DriverTrip(String day, Boolean toUni, Boolean arriveBy, String time, Boolean repeating, String departingAddress, ArrayList<User> riders) {
        super(day, toUni, arriveBy, time, repeating, departingAddress);
        this.riders = riders;
    }

    public void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out, flags);
        out.writeParcelableArray((User[]) riders.toArray(), 0);
    }

    public static final Parcelable.Creator<Trip> CREATOR
            = new Parcelable.Creator<Trip>() {
        public Trip createFromParcel(Parcel in) {
            return new DriverTrip(in);
        }

        public Trip[] newArray(int size) {
            return new Trip[size];
        }
    };

    private DriverTrip(Parcel in) {
        super(in);
        riders = new ArrayList<User>(Arrays.asList((User[]) in.readParcelableArray(User.class.getClassLoader())));
    }

    public ArrayList<User> getRiders() {
        return riders;
    }

    public String getDepartingAddress() {
        return departingAddress;
    }

    public void setDepartingAddress(String departingAddress) {
        this.departingAddress = departingAddress;
    }

    public void addRider(User rider) {

        this.riders.add(rider);
    }

    public void removeRider(User rider){
        this.riders.remove(rider);
    }
}
