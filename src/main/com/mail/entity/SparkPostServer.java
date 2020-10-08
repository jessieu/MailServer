package com.mail.entity;

import com.sparkpost.Client;
import com.sparkpost.exception.SparkPostException;
import com.sparkpost.model.AddressAttributes;
import com.sparkpost.model.RecipientAttributes;
import com.sparkpost.model.TemplateContentAttributes;
import com.sparkpost.model.TransmissionWithRecipientArray;
import com.sparkpost.model.responses.TransmissionCreateResponse;
import com.sparkpost.resources.ResourceTransmissions;
import com.sparkpost.transport.IRestConnection;
import com.sparkpost.transport.RestConnection;

import java.util.ArrayList;
import java.util.List;

public class SparkPostServer implements MailServer {
    private final TransmissionWithRecipientArray transmission;
    private final TemplateContentAttributes contentAttributes;
    private final Client client;

    public SparkPostServer() {
        client = new Client(System.getenv("SPARKPOST_API_KEY"));
        this.transmission = new TransmissionWithRecipientArray();
        this.contentAttributes = new TemplateContentAttributes();
    }

    @Override
    public void setMessage(Message message) {
        contentAttributes.setFrom(new AddressAttributes(message.getSender()));
        // Populate Recipients
        List<RecipientAttributes> recipientArray = new ArrayList<>();
        for (String recipient : message.getRecipient()) {
            RecipientAttributes recipientAttribs = new RecipientAttributes();
            recipientAttribs.setAddress(new AddressAttributes(recipient));
            recipientArray.add(recipientAttribs);
        }
        transmission.setRecipientArray(recipientArray);
        // populate message body
        contentAttributes.setSubject(message.getSubject());
        contentAttributes.setHtml(message.getHtml());

        transmission.setContentAttributes(this.contentAttributes);
    }

    public boolean sendMail() {
        // Send the Email
        TransmissionCreateResponse transmissionResponse = null;
        try {
            IRestConnection connection = new RestConnection(this.client);
            transmissionResponse = ResourceTransmissions.create(connection, 0, transmission);
        } catch (SparkPostException e) {
            e.printStackTrace();
        }

        assert transmissionResponse != null;
        if (transmissionResponse.getResponseCode() == 200) {
            // Message successfully sent
            System.out.println("Message Sent Successfully");
            return true;
        } else {
            // An error occurred
            System.out.println("Fail to Send Message");
            System.err.println("TRANSMISSION ERROR: " + transmissionResponse);
            System.out.println("Looking for other server....");
        }
        return false;
    }
}
