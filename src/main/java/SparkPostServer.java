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
//        String API = "a0efeda6ce232787548b6f1a7831119904459d95";
        client = new Client(System.getenv("SPARKPOST_API_KEY"));
        this.transmission = new TransmissionWithRecipientArray();
        this.contentAttributes = new TemplateContentAttributes();
    }

    public void setMessage(Message message) {
        contentAttributes.setFrom(new AddressAttributes(message.getSender()));
        // Populate Recipients
        List<RecipientAttributes> recipientArray = new ArrayList<RecipientAttributes>();
        for (String recipient : message.getRecipient()) {
            RecipientAttributes recipientAttribs = new RecipientAttributes();
            recipientAttribs.setAddress(new AddressAttributes(recipient));
            recipientArray.add(recipientAttribs);
        }
        transmission.setRecipientArray(recipientArray);
        // populate message body
        contentAttributes.setSubject(message.getSubject());
        contentAttributes.setText(message.getText());
        contentAttributes.setHtml(message.getHtml());

        transmission.setContentAttributes(this.contentAttributes);
    }

    public boolean sendMail() {
        // Send the Email
        IRestConnection connection = null;
        TransmissionCreateResponse transmissionResponse = null;
        try {
            connection = new RestConnection(this.client);
            transmissionResponse = ResourceTransmissions.create(connection, 0, transmission);
        } catch (SparkPostException e) {
            e.printStackTrace();
        }

        assert transmissionResponse != null;
        if (transmissionResponse.getResponseCode() == 200) {
            // Message successfully sent
            System.out.println("Message Sent Successfully");
//            System.out.println("Transmission Response: " + transmissionResponse);
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
