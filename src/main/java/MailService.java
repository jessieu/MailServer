public class MailService {
    public static long TIMEOUT = 300; // in second
    private Message message;
    private MailServer[] mailServer;

    public  MailService() {
        message = new Message();
        mailServer = new MailServer[]{new SendGridServer(), new SparkPostServer()};
    }
    public MailService(String from, String[] to, String text, String subject, String html) {
        this();
        message.setSender(from);
        message.setRecipient(to);
        message.setText(text);
        message.setSubject(subject);
        message.setHtml(html);
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public MailServer[] getMailServer() {
        return mailServer;
    }

    public void setMailServer(MailServer[] mailServer) {
        this.mailServer = mailServer;
    }

    public void sendEmail() {
        boolean sent = false;
        for (int i = 0; i < this.mailServer.length && !sent; i++) {
            MailServer server = this.mailServer[i];
            server.setMessage(this.message);
            sent = server.sendMail();
        }

        if (!sent) {
            System.out.println("Current Mail Service is down. Please wait a few minutes and try again");
            System.exit(-1);
        }
    }
}
