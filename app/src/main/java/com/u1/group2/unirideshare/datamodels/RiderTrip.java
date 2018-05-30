package com.u1.group2.unirideshare.datamodels;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;


public class RiderTrip extends Trip implements Parcelable {
    private User driver;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RiderTrip riderTrip = (RiderTrip) o;
        return Objects.equals(driver, riderTrip.driver);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), driver);
    }

    public RiderTrip(String day, Boolean toUni, Boolean arriveBy, String time, Boolean repeating, String departingAddress) {
        super(day, toUni, arriveBy, time, repeating, departingAddress);
    }

    public void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out, flags);
        out.writeParcelable(driver, 0);
    }

    public static final Parcelable.Creator<Trip> CREATOR
            = new Parcelable.Creator<Trip>() {
        public Trip createFromParcel(Parcel in) {
            return new RiderTrip(in);
        }

        public Trip[] newArray(int size) {
            return new Trip[size];
        }
    };

    private RiderTrip(Parcel in) {
        super(in);
        driver = in.readParcelable(User.class.getClassLoader());
    }

    public User getDriver() {
        return driver;
    }

    public void setDriver(User driver) {
        this.driver = driver;
    }

    public String getDepartingAddress() {
        return departingAddress;
    }

    public void setDepartingAddress(String departingAddress) {
        this.departingAddress = departingAddress;
    }
}
