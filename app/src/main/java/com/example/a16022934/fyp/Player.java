package com.example.a16022934.fyp;

import java.io.Serializable;
import java.util.Date;

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
}
