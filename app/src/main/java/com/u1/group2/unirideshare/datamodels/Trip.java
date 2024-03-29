package com.u1.group2.unirideshare.datamodels;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class Trip implements Parcelable, Comparable<Trip>{

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

    @Override
    public int compareTo(Trip anotherTrip) {
        if (this.equals(anotherTrip)) {
            return 0;
        } else if (this.day.equals(anotherTrip.day)){
            return this.time.compareTo(anotherTrip.time);
        } else {
            int tripDay = parseDayOfWeek(this.day, Locale.US);
            int anotherTripDay = parseDayOfWeek(anotherTrip.day, Locale.US);
            if (tripDay < anotherTripDay) {
                return -1;
            } else if (tripDay > anotherTripDay) {
                return 1;
            }
        }
        return 0;
    }

    private static int parseDayOfWeek(String day, Locale locale) {
        try {
            SimpleDateFormat dayFormat = new SimpleDateFormat("E", locale);
            Date date = dayFormat.parse(day);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar.get(Calendar.DAY_OF_WEEK);
        } catch (Exception  e) {
            return 0;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trip = (Trip) o;
        return Objects.equals(day, trip.day) &&
                Objects.equals(toUni, trip.toUni) &&
                Objects.equals(arriveBy, trip.arriveBy) &&
                Objects.equals(time, trip.time) &&
                Objects.equals(repeating, trip.repeating) &&
                Objects.equals(departingAddress, trip.departingAddress);
    }

    @Override
    public int hashCode() {

        return Objects.hash(day, toUni, arriveBy, time, repeating, departingAddress);
    }
}
