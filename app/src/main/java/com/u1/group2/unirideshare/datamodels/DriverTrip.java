package com.u1.group2.unirideshare.datamodels;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class DriverTrip extends Trip {

    private ArrayList<User> riders;


    public DriverTrip(String day, Boolean toUni, Boolean arriveBy, String time, Boolean repeating, String departingAddress, ArrayList<User> riders) {
        super(day, toUni, arriveBy, time, repeating, departingAddress);
        this.riders = riders;
    }

    public void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out, flags);
        User[] ridersArray = riders.toArray(new User[riders.size()]);
        out.writeParcelableArray(ridersArray, 0);
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
        Parcelable[] resultArray = in.readParcelableArray(User.class.getClassLoader());
        if (resultArray != null) {
            User[] ridersArray = Arrays.copyOf(resultArray, resultArray.length, User[].class);
            riders = new ArrayList<User>(Arrays.asList(ridersArray));
        } else {
            riders = new ArrayList<User>();
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DriverTrip that = (DriverTrip) o;
        return Objects.equals(riders, that.riders);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), riders);
    }
}
