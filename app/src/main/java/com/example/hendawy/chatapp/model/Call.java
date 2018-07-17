package com.example.hendawy.chatapp.model;

public class Call {

    private String jid;
    private long lastCallTimeStamp;
    private CallType lastCallType;

    public Call(String jid, Long lastCallTimeStamp, CallType lastCallType) {
        this.jid = jid;
        this.lastCallTimeStamp = lastCallTimeStamp;
        this.lastCallType = lastCallType;
    }

    public String getJid() {
        return jid;
    }

    public void setJid(String jid) {
        this.jid = jid;
    }

    public Long getLastCallTimeStamp() {
        return lastCallTimeStamp;
    }

    public void setLastCallTimeStamp(Long lastCallTimeStamp) {
        this.lastCallTimeStamp = lastCallTimeStamp;
    }

    public CallType getLastCallType() {
        return lastCallType;
    }

    public void setLastCallType(CallType lastCallType) {
        this.lastCallType = lastCallType;
    }

    public String getCallTypeValue(CallType callType) {
        if (callType == CallType.OUT_GOING) {
            return "OUT_GOING";
        } else if (callType == CallType.IN_COMING) {
            return "IN_COMING";
        } else {
            return null;
        }
    }


    public enum CallType {
        OUT_GOING, IN_COMING

    }
}
