public interface MailServer {
    public void setMessage(Message message);
    public boolean sendMail(); // return status code
}
