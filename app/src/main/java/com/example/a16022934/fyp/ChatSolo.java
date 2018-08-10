package com.example.a16022934.fyp;

import java.io.Serializable;

public class ChatSolo implements Serializable{
    private String msgID;
    private String senderName;
    private String receiverName;
    private String senderId;
    private String receiverId;
    private String msg;
    private long timeStamp;

    public ChatSolo(){
    }

    public ChatSolo(String msgID, String senderName, String receiverName, String senderId, String receiverId, String msg, long timeStamp) {
        this.msgID = msgID;
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.msg = msg;
        this.timeStamp = timeStamp;
    }

    public String getMsgID() {
        return msgID;
    }

    public void setMsgID(String msgID) {
        this.msgID = msgID;
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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
