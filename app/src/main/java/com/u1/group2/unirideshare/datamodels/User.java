package com.u1.group2.unirideshare.datamodels;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class User implements Parcelable {

    private String firstName;
    private String lastName;
    private String emailAddress;
    private String homeAddress;
    private String uni;
    private String licencePlate;
    private Integer age;
    private Integer rating;

    public User(String firstName, String lastName, String emailAddress, String homeAddress, String uni, Integer age, Integer rating, String licencePlate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.homeAddress = homeAddress;
        this.uni = uni;
        this.licencePlate = licencePlate;
        this.age = age;
        this.rating = rating;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(emailAddress, user.emailAddress) &&
                Objects.equals(homeAddress, user.homeAddress) &&
                Objects.equals(uni, user.uni) &&
                Objects.equals(licencePlate, user.licencePlate) &&
                Objects.equals(age, user.age);
    }

    @Override
    public int hashCode() {

        return Objects.hash(firstName, lastName, emailAddress, homeAddress, uni, licencePlate, age);
    }

    public String getUni() {
        return uni;
    }

    public void setUni(String uni) {
        this.uni = uni;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }



    protected User(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        emailAddress = in.readString();
        homeAddress = in.readString();
        uni = in.readString();
        licencePlate = in.readString();
        age = in.readInt();
        rating = in.readInt();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(emailAddress);
        dest.writeString(homeAddress);
        dest.writeString(uni);
        dest.writeString(licencePlate);
        dest.writeInt(age);
        dest.writeInt(rating);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}