package com.example.a16022934.fyp;

import android.text.format.DateFormat;
import android.util.Log;

import java.io.Serializable;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Player implements Serializable {
    private String dateOfBirth;
    private String description;
    private String email;
    private String fullName;
    private String gender;
    private int phoneNo;
    private String ratingId;
    private String selfEvalId;
    private String user_id;

    public Player() {
    }

    public Player(String dateOfBirth, String description, String email, String fullName, String gender, int phoneNo, String ratingId, String selfEvalId, String user_id) {
        this.dateOfBirth = dateOfBirth;
        this.description = description;
        this.email = email;
        this.fullName = fullName;
        this.gender = gender;
        this.phoneNo = phoneNo;
        this.ratingId = ratingId;
        this.selfEvalId = selfEvalId;
        this.user_id = user_id;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(int phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getRatingId() {
        return ratingId;
    }

    public void setRatingId(String ratingId) {
        this.ratingId = ratingId;
    }

    public String getSelfEvalId() {
        return selfEvalId;
    }

    public void setSelfEvalId(String selfEvalId) {
        this.selfEvalId = selfEvalId;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getAge() {
        String strDate = getDateOfBirth();
        String[] dmy = strDate.split("/");
        Calendar today = Calendar.getInstance();
        Calendar dob = Calendar.getInstance();
        int day = Integer.parseInt(dmy[0]);
        int month = Integer.parseInt(dmy[1]);
        int year = Integer.parseInt(dmy[2]);
        dob.set(year, month, day);
        Log.v("Name", getFullName());
        Log.v("DOB String", dob.toString());
        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if (age != 0) {
            if (today.get(Calendar.MONTH) < dob.get(Calendar.MONTH)) {
                age = age - 1;
            } else if (today.get(Calendar.DAY_OF_MONTH) == dob.get(Calendar.DAY_OF_MONTH)) {
                age = age - 1;
            }
        }


        Log.v("age after day", age + "");

        return age;
    }
}
