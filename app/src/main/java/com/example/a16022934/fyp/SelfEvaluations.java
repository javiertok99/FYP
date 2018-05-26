package com.example.a16022934.fyp;

public class SelfEvaluations {
    private int backHand;
    private int dropShot;
    private int frontHand;
    private int service;
    private int smashShot;
    private int user_id;

    public SelfEvaluations() {

    }

    public SelfEvaluations(int backHand, int dropShot, int frontHand, int service, int smashShot, int user_id) {
        this.backHand = backHand;
        this.dropShot = dropShot;
        this.frontHand = frontHand;
        this.service = service;
        this.smashShot = smashShot;
        this.user_id = user_id;
    }

    public int getBackHand() {
        return backHand;
    }

    public void setBackHand(int backHand) {
        this.backHand = backHand;
    }

    public int getDropShot() {
        return dropShot;
    }

    public void setDropShot(int dropShot) {
        this.dropShot = dropShot;
    }

    public int getFrontHand() {
        return frontHand;
    }

    public void setFrontHand(int frontHand) {
        this.frontHand = frontHand;
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

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
