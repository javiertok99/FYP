package com.example.a16022934.fyp;

public class SelfEvaluations {
    private int backhand;
    private int dropShot;
    private int fronthand;
    private int service;
    private int smashShot;
    private String user_id;

    public SelfEvaluations() {

    }

    public SelfEvaluations(int backHand, int dropShot, int frontHand, int service, int smashShot, String user_id) {
        this.backhand = backHand;
        this.dropShot = dropShot;
        this.fronthand = frontHand;
        this.service = service;
        this.smashShot = smashShot;
        this.user_id = user_id;
    }

    public int getBackHand() {
        return backhand;
    }

    public void setBackHand(int backHand) {
        this.backhand = backHand;
    }

    public int getDropShot() {
        return dropShot;
    }

    public void setDropShot(int dropShot) {
        this.dropShot = dropShot;
    }

    public int getFrontHand() {
        return fronthand;
    }

    public void setFrontHand(int frontHand) {
        this.fronthand = frontHand;
    }

    public int getService() {
        return service;
    }

    public void setService(int service) {
        this.service = service;
    }

    public int getSmashShot() {
        return smashShot;
    }

    public void setSmashShot(int smashShot) {
        this.smashShot = smashShot;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
