package com.example.config

import java.util.Properties
import javax.mail.internet.{InternetAddress, MimeMessage}
import javax.mail.{Message, PasswordAuthentication, Session, Transport}

import com.example.domain.User


/**
 * Created by julian.rodriguez on 10/08/2015.
 */
class MailConfig {

  val username = "julian.rodriguez@ceiba.com.co"
  val password = "q-e-t-u-24-s*"

  val props = new Properties()
  props.put("mail.smtp.auth", "true")
  props.put("mail.smtp.starttls.enable", "true")
  props.put("mail.smtp.host", "smtp.gmail.com")
  props.put("mail.smtp.port", "587")

  val session = Session.getInstance(props,
    new javax.mail.Authenticator() {
        override def getPasswordAuthentication : PasswordAuthentication = {
        return new PasswordAuthentication(username, password)
      }
    })

  def send (user : User) = {
    val message = new MimeMessage(session)
    message.setFrom(new InternetAddress(username))
    message.setRecipients(Message.RecipientType.TO, user.email)
    message.setSubject("Welcome")
    message.setText(
      s"""
        Welcome ${user.name} to FruitStore

        User Name: ${user.name}
        User Email: ${user.email}
        User Password : ${user.pass}

        Have a nice day.

        Admin: $username
      """)
    Transport.send(message)
  }
}
