package com.example.a16022934.fyp;

public class Ratings {
    private int score;
    private String user_id;

    public Ratings() {

    }

    public Ratings(int score, String user_id) {
        this.score = score;
        this.user_id = user_id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}