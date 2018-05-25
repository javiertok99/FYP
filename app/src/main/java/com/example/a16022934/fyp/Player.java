package com.example.a16022934.fyp;

import java.io.Serializable;
import java.util.Date;

public class Player implements Serializable {
    private String dateOfBirth;
    private String description;
    private String email;
    private String firstName;
    private String gender;
    private String lastName;
    private int phoneNo;
    private String ratingId;
    private String selfEvalId;

    public Player() {
    }

    public Player(String dateOfBirth, String description, String email, String firstName, String gender, String lastName, int phoneNo, String ratingId, String selfEvalId) {
        this.dateOfBirth = dateOfBirth;
        this.description = description;
        this.email = email;
        this.firstName = firstName;
        this.gender = gender;
        this.lastName = lastName;
        this.phoneNo = phoneNo;
        this.ratingId = ratingId;
        this.selfEvalId = selfEvalId;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
}
