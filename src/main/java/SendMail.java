import com.sparkpost.Client;
import com.sparkpost.exception.SparkPostException;
public class SendMail {

    public static void main(String[] args) throws SparkPostException {
        String html = "<h1>Please select your available time from the following</h1>" +
                "<ol>" +
                "<li>Oct 10 9:00 - 10:00am</li>" +
                "<li>Oct 11 1:30 - 2:30pm</li>" +
                "<li>Oct 12 2:00 - 3:00pm</li>" +
                "</ol>" +
                "<p>Looking forward to see you</p>";
        MailService service = new MailService("mymail@weilanyu.io", new String[]{"wlblueblueyu@gmail.com"}, "", "Interview Appointment", html);
        service.sendEmail();
    }
}
