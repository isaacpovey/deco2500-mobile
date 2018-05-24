package com.u1.group2.unirideshare.datamodels;

import android.os.Parcel;
import android.os.Parcelable;

public class Trip implements Parcelable {

    String day;
    Boolean toUni;
    Boolean arriveBy;
    String time;
    Boolean repeating;
    String departingAddress;

    public Trip(String day, Boolean toUni, Boolean arriveBy, String time, Boolean repeating, String departingAddress) {
        this.day = day;
        this.toUni = toUni;
        this.arriveBy = arriveBy;
        this.time = time;
        this.repeating = repeating;
        this.departingAddress = departingAddress;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(day);
        out.writeInt(toUni ? 1 : 0);
        out.writeInt(arriveBy ? 1 : 0);
        out.writeString(time);
        out.writeInt(repeating ? 1 : 0);
        out.writeString(departingAddress);
    }

    public static final Parcelable.Creator<Trip> CREATOR
            = new Parcelable.Creator<Trip>() {
        public Trip createFromParcel(Parcel in) {
            return new Trip(in);
        }

        public Trip[] newArray(int size) {
            return new Trip[size];
        }
    };

    protected Trip(Parcel in) {
        day = in.readString();
        toUni = (in.readInt() == 1);
        arriveBy = (in.readInt() == 1);
        time = (in.readString());
        repeating = (in.readInt() == 1);
        departingAddress = in.readString();
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Boolean getToUni() {
        return toUni;
    }

    public void setToUni(Boolean toUni) {
        this.toUni = toUni;
    }

    public Boolean getArriveBy() {
        return arriveBy;
    }

    public void setArriveBy(Boolean arriveBy) {
        this.arriveBy = arriveBy;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Boolean getRepeating() {
        return repeating;
    }

    public void setRepeating(Boolean repeating) {
        this.repeating = repeating;
    }
}
