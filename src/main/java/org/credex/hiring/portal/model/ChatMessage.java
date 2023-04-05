package org.credex.hiring.portal.model;

import javax.persistence.*;


public class ChatMessage {



    private int userId;

    private String emailId;


    private String message;


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ChatMessage (String message,  String emailId, int userId) {
        this.userId = userId;
        this.emailId = emailId;
        this.message = message;
    }
}
