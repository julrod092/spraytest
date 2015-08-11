package com.example.controller

import javax.mail.internet.{InternetAddress, MimeMessage}
import javax.mail.{Message, MessagingException, Transport}

import com.example.config.MailConfig
import com.example.domain.Email

/**
 * Created by sebastian.garces on 10/08/2015.
 */
class MailController {

  val username = "tiendafrutas@gmail.com"

  def sendMail(client: String, subject: String, bodyHtml: String) = {
    val context = new Email(recipient = client,subject = subject)
    try {
      sendEMail(context, bodyHtml);
    } catch {
      case e: MessagingException => e.printStackTrace()
    }
  }

  @throws(classOf[MessagingException])
  def sendEMail(email: Email, bodyHtml: String) {
    val mailConfig = new MailConfig
    val message = new MimeMessage(mailConfig.config)
    message.setFrom(new InternetAddress(username))
    message.setRecipient(Message.RecipientType.TO, new InternetAddress(email.recipient))
    message.setSubject(email.subject)
    message.setText(bodyHtml)
    Transport.send(message)
  }
}