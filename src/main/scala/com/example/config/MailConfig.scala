package com.example.config

import java.util.Properties
import javax.mail.{PasswordAuthentication, Session}

import com.typesafe.config.ConfigFactory


/**
 * Created by julian.rodriguez on 10/08/2015.
 */
class MailConfig {

  val conf = ConfigFactory.load()
  val smtp = conf.getConfig("smtpGmailConfiguration")

  def config : Session = {

    val props = new Properties()
    props.put("mail.smtp.auth", "true")
    props.put("mail.smtp.starttls.enable", "true")
    props.put("mail.smtp.host", "smtp.gmail.com")
    props.put("mail.smtp.port", "587")

    val session = Session.getInstance(props,
      new javax.mail.Authenticator() {
        override def getPasswordAuthentication: PasswordAuthentication = {
          return new PasswordAuthentication(smtp.getString("username"), smtp.getString("password"))
        }
      })
    session
  }
}
