package com.mail.entity;

import com.mail.entity.Message;

public interface MailServer {
    void setMessage(Message message); // main logic for sending email
    boolean sendMail(); // return success or not
}
