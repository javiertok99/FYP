package com.example.a16022934.fyp;

import java.sql.Time;
import java.util.Date;

public class MatchHistory {
    String player1;
    String player2;
    String player3;
    String player4;
    String dateOfMatch;
    String timeOfMatch;
    String location;

    public MatchHistory(String player1, String player2, String dateOfMatch, String timeOfMatch, String location) {
        this.player1 = player1;
        this.player2 = player2;
        this.dateOfMatch = dateOfMatch;
        this.timeOfMatch = timeOfMatch;
        this.location = location;
    }

    public MatchHistory(String player1, String player2, String player3, String player4, String dateOfMatch, String timeOfMatch, String location) {
        this.player1 = player1;
        this.player2 = player2;
        this.player3 = player3;
        this.player4 = player4;
        this.dateOfMatch = dateOfMatch;
        this.timeOfMatch = timeOfMatch;
        this.location = location;
    }

    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public String getPlayer3() {
        return player3;
    }

    public void setPlayer3(String player3) {
        this.player3 = player3;
    }

    public String getPlayer4() {
        return player4;
    }

    public void setPlayer4(String player4) {
        this.player4 = player4;
    }

    public String getDateOfMatch() {
        return dateOfMatch;
    }

    public void setDateOfMatch(String dateOfMatch) {
        this.dateOfMatch = dateOfMatch;
    }

    public String getTimeOfMatch() {
        return timeOfMatch;
    }

    public void setTimeOfMatch(String timeOfMatch) {
        this.timeOfMatch = timeOfMatch;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
