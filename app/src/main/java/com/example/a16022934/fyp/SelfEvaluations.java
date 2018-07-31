package com.example.a16022934.fyp;

public class SelfEvaluations {
    private int backhand;
    private int dropShot;
    private int fronthand;
    private int service;
    private int smashShot;
    private int footWork;
    private String user_id;


    public SelfEvaluations() {

    }

    public SelfEvaluations(int backhand, int dropShot, int fronthand, int service, int smashShot, int footWork, String user_id) {
        this.backhand = backhand;
        this.dropShot = dropShot;
        this.fronthand = fronthand;
        this.service = service;
        this.smashShot = smashShot;
        this.footWork = footWork;
        this.user_id = user_id;
    }

    public int getBackhand() {
        return backhand;
    }

    public void setBackhand(int backhand) {
        this.backhand = backhand;
    }

    public int getDropShot() {
        return dropShot;
    }

    public void setDropShot(int dropShot) {
        this.dropShot = dropShot;
    }

    public int getFronthand() {
        return fronthand;
    }

    public void setFronthand(int fronthand) {
        this.fronthand = fronthand;
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

    public int getFootWork() {
        return footWork;
    }

    public void setFootWork(int footWork) {
        this.footWork = footWork;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
