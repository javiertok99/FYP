package com.example.a16022934.fyp;

import java.io.Serializable;

public class Player implements Serializable {
    private int res;
    private String name;
    private String gender;
    private int age;
    private String location;

    public Player(int res, String name, int age, String location, String gender) {
        this.res = res;
        this.name = name;
        this.age = age;
        this.location = location;
        this.gender = gender;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
