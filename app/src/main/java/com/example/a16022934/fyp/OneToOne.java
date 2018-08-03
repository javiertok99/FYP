package com.example.a16022934.fyp;

import android.support.annotation.NonNull;

import java.io.Serializable;

public class OneToOne implements Serializable{
    private String senderName;
    private String receiverName;
    private String senderId;
    private String receiverId;

    public OneToOne(String senderName, String receiverName, String senderId, String receiverId) {
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.senderId = senderId;
        this.receiverId = receiverId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

}
