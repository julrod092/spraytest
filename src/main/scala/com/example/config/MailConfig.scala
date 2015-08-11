package com.example.config

import java.util.Properties
import javax.mail.{PasswordAuthentication, Session}


/**
 * Created by julian.rodriguez on 10/08/2015.
 */
class MailConfig {

  def config : Session = {
    val username = "tiendafrutas@gmail.com"
    val password = "tiendafrutas123"
    val props = new Properties()
    props.put("mail.smtp.auth", "true")
    props.put("mail.smtp.starttls.enable", "true")
    props.put("mail.smtp.host", "smtp.gmail.com")
    props.put("mail.smtp.port", "587")

    val session = Session.getInstance(props,
      new javax.mail.Authenticator() {
        override def getPasswordAuthentication: PasswordAuthentication = {
          return new PasswordAuthentication(username, password)
        }
      })
    session
  }
}
