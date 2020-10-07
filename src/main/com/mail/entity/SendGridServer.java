package com.mail.entity;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;

import java.io.IOException;

public class SendGridServer implements MailServer {
    private final SendGrid sg;
    private Request request;
    private Mail mail;
    public SendGridServer() {
        sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
        request = new Request();
        mail = new Mail();
    }

    @Override
    public void setMessage(Message message) {
        Email from = new Email(message.getSender());
        Personalization personalization = new Personalization();
        for (String recipient : message.getRecipient()) {
            Email to = new Email(recipient);
            personalization.addTo(to);
        }
        Content content = new Content("text/html", message.getHtml());
        mail.setFrom(from);
        mail.addPersonalization(personalization);
        mail.setSubject(message.getSubject());
        mail.addContent(content);
    }

    public boolean sendMail() {
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request); // send email
            if (response.getStatusCode() == 202) {
                System.out.println("Message has been sent successfully");
                return true;
            } else {
                System.err.println("Fail to Send Message");
                System.err.println(response.getBody());
                System.out.println("Looking for other server....");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
