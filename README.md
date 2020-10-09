## MailService

### Overview

This project provide a private service that accepts the necessary information from user and sends emails. There are some email providers avaiable in this service, including but not limit to SendGrid and SparkPost. If one of the services goes down, the application can find next available email provider to the it.

![](https://i.loli.net/2020/10/09/Ge5UVcgCZSJ2bkd.gif)

### Prerequisites

- A personal domain name for sending email
- Accounts in any of the following Email Provider(This project only uses SendGrid and SparkPost):
  - [SendGrid](https://sendgrid.com/user/signup) - [Simple Send Documentation](https://sendgrid.com/docs/API_Reference/Web_API/mail.html)
  - [Mailgun](http://www.mailgun.com/) - [Simple Send Documentation](http://documentation.mailgun.com/quickstart.html#sending-messages)
  - [SparkPost](https://www.sparkpost.com/) - [Developer Hub](https://developers.sparkpost.com/)
  - [Amazon SES](http://aws.amazon.com/ses/) - [Simple Send Documentation](http://docs.aws.amazon.com/ses/latest/APIReference/API_SendEmail.html)
- API Key from Email Provider (Will be provided after verification of domain name)

### Development Environment

- Java 1.8
- IDEA
  - Note: to avoid API Key exposure in public, we can add API key to IDEA's project environment setting and then use `System.getenv(key)` in Java to get it back.

### Project Archicture

The project is only designed for personal use as you can see in the demo. I hardcoded my email address and only have necessary fields, recipients, subject and email content in the email form. First I am trying to keep thing simple, but able to build more stuffs with this base. Second it's because of the API key requirement. It requires higher security knowledge and robust design.	

My initial attempt is to implement it as a console-based application, but it's inefficient to take user input from console line by line and difficult to make any changes after user finished typing. And thus, I refactor it as a web application and implement with Java Servlet and JSP. Though it's outdated and heavyweight, it's simple to use. I might convert it to a Spring project later on. 

​	This project follows MVC pattern.

- View: user can view email form, and write an email
- Model: transfer users' email information to controller
- Controller: send the email via available email providers.

The reason why I chose MVC pattern is because it enhance decoupling of components. 

### Improvement

If I have more time, I would design a multi-users mail service with database support. 

In order to access these email providers, each user needs to provide different API keys for different providers. My intuition is to use database to store user information, including name, email address recognized in those email providers, and API key for each provider. That requires user to register and send email after login.     

<img src="https://i.loli.net/2020/10/09/KjtpLYq2wJ89NXV.png"  />

