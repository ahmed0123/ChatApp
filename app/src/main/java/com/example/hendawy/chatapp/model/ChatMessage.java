package com.example.hendawy.chatapp.model;

import android.text.format.DateFormat;

import java.util.concurrent.TimeUnit;



public class ChatMessage {
    private String message;
    private long timestamp;
    private Type type;
    private String contactJid;


    public ChatMessage(String message, long timestamp, Type type , String contactJid){
        this.message = message;
        this.timestamp = timestamp;
        this.type = type;
        this.contactJid = contactJid;

    }

    public String getMessage() {
        return message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Type getType() {
        return type;
    }

    public String getContactJid() {
        return contactJid;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setContactJid(String contactJid) {
        this.contactJid = contactJid;
    }

    public String getFormattedTime(){

        long oneDayInMillis = TimeUnit.DAYS.toMillis(1); // 24 * 60 * 60 * 1000;

        long timeDifference = System.currentTimeMillis() - timestamp;

        return timeDifference < oneDayInMillis
                ? DateFormat.format("hh:mm a", timestamp).toString()
                : DateFormat.format("dd MMM - hh:mm a", timestamp).toString();
    }



    public enum  Type {
        SENT,RECEIVED
    }


}
