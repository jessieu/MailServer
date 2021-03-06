package com.mail.entity;

public class Message {
    private String[] recipient;
    private String sender; // will not change in current project
    private String subject;
    private String html;


    public String[] getRecipient() {
        return recipient;
    }

    public void setRecipient(String[] recipient) {
        this.recipient = recipient;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }
}
