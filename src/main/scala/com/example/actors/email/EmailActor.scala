package com.example.actors.email

import com.example.domain.User

import akka.actor.Actor
import java.util.Properties
import javax.mail._
import javax.mail.internet._

/**
 * @author luis.saldarriaga
 */
class EmailActor(user:User){

  def receive = {
      val FROM: String = "micredito@rapicredit.com"
      val TO: String = user.email
      val BODY: String = "This email was sent through Amazon SES by using the AWS SDK for Java."
      val SUBJECT: String = "Amazon SES test (AWS SDK for Java)"

      // Supply your SMTP credentials below. Note that your SMTP credentials are different from your AWS credentials.
      val SMTP_USERNAME: String = "AKIAIANTYK66JTHF6BHQ"; // Replace with your SMTP username.
      val SMTP_PASSWORD: String = "AsiTb8sszcBSsc89Z+N4ehIuo9i/T7A5/TMvXGZ8XKAU"; // Replace with your SMTP password.

      // Amazon SES SMTP host name. This example uses the US West (Oregon) region.
      val HOST: String = "email-smtp.us-east-1.amazonaws.com";

      // Port we will connect to on the Amazon SES SMTP endpoint. We are choosing port 25 because we will use
      // STARTTLS to encrypt the connection.
      val PORT = Some(465);

      // Create a Properties object to contain connection configuration information.
      val props: Properties = System.getProperties();
      props.put("mail.transport.protocol", "smtp");
      props.put("mail.smtp.port", PORT);

      // Set properties indicating that we want to use STARTTLS to encrypt the connection.
      // The SMTP session will begin on an unencrypted connection, and then the client
      // will issue a STARTTLS command to upgrade to an encrypted connection.
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.starttls.required", "true");

      // Create a Session object to represent a mail session with the specified properties. 
      val session: Session = Session.getDefaultInstance(props);

      // Create a message with the specified information. 
      val msg: MimeMessage = new MimeMessage(session);
      msg.setFrom(new InternetAddress(FROM));
      msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
      msg.setSubject(SUBJECT);
      msg.setContent(BODY, "text/plain");

      // Create a transport.        
      val transport: Transport = session.getTransport();

      // Send the message.
      try {
        System.out.println("Attempting to send an email through the Amazon SES SMTP interface...");

        // Connect to Amazon SES using the SMTP username and password you specified above.
        transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);

        // Send the email.
        transport.sendMessage(msg, msg.getAllRecipients());
        System.out.println("Email sent!");
      } catch {
        case e: Exception =>
          System.out.println("The email was not sent.");
          System.out.println("Error message: " + e.getMessage());
      } finally {
        // Close and terminate the connection.
        transport.close();
      }
  }
}