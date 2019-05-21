package com.bw.myproduct.bean;

public class EventMessage {

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public EventMessage(int userId,String  sessionId) {
        this.userId = userId;
        this.sessionId=sessionId;
    }

    String message;
    int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    String  sessionId;
}
