package com.example.a16022934.fyp;

public class TopPlayers {
    private int res;
    private String name;
    private String gender;
    private int age;
    private String placeOfBirth;
    private String description;

    public TopPlayers() {
    }

    public TopPlayers(int res, String name, String gender, int age, String placeOfBirth, String description) {
        this.res = res;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.placeOfBirth = placeOfBirth;
        this.description = description;
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

    public String getplaceOfBirth() {
        return placeOfBirth;
    }

    public void setplaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
