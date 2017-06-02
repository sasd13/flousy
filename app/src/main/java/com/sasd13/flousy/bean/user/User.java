package com.sasd13.flousy.bean.user;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ssaidali2 on 27/05/2017.
 */

public class User implements Parcelable {

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    private String userID, intermediary, email;
    private UserPreferences userPreferences;

    public User() {
    }

    protected User(Parcel in) {
        userID = in.readString();
        intermediary = in.readString();
        email = in.readString();
        userPreferences = in.readParcelable(UserPreferences.class.getClassLoader());
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getIntermediary() {
        return intermediary;
    }

    public void setIntermediary(String intermediary) {
        this.intermediary = intermediary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserPreferences getUserPreferences() {
        return userPreferences;
    }

    public void setUserPreferences(UserPreferences userPreferences) {
        this.userPreferences = userPreferences;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(userID);
        parcel.writeString(intermediary);
        parcel.writeString(email);
        parcel.writeParcelable(userPreferences, i);
    }
}
