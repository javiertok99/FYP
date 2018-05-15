package com.example.a16022934.fyp;

public class TopPlayers {
    private int res;
    private String name;
    private String gender;
    private int age;
    private String location;

    public TopPlayers(int res, String name, String gender, int age, String location) {
        this.res = res;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.location = location;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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
}
