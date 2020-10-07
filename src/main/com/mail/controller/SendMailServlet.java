package com.mail.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.mail.service.MailService;

@WebServlet("/sendMail")
public class SendMailServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String recipients = request.getParameter("recipients");
        String[] allRecipients = recipients.split("[,;]+");

        String subject = request.getParameter("subject");
        String sender = request.getParameter("sender");
        String content = request.getParameter("content");

        // Format message and send email
        MailService mailService = new MailService(sender, allRecipients, subject, content);
        boolean sent = mailService.sendEmail();

        // redirect to receipt page to indicate whether email has been sent or not
        String receiptPage = "receipt.jsp";
        request.setAttribute("success", sent);
        RequestDispatcher dispatcher = request.getRequestDispatcher(receiptPage);
        dispatcher.forward(request, response);
    }
}
