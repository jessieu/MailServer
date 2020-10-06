//public class MailGunServer implements MailServer{
//    private Client client;
//    private MultivaluedMapImpl formData;
//    public MailGunServer() {
//        client = Client.create();
//        client.addFilter(new HTTPBasicAuthFilter("api", "4b4b28ddfedc4aecc8e95a328000d60e-0d2e38f7-19896688"));
//        formData = new MultivaluedMapImpl();
//    }
//    public void setMessage(Message message) {
//        formData.add("from", message.getSender());
//        formData.add("to", message.getRecipient());
//        formData.add("subject", message.getSubject());
//        formData.add("text", message.getHtml());
//    }
//
//    public boolean sendMail() {
//
//        return false;
//    }
//}
