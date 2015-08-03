package com.example.domain

import spray.httpx.SprayJsonSupport
import spray.json.DefaultJsonProtocol

/**
 * Created by julian.rodriguez on 30/07/2015.
 */
case class User (name : String, email : String, pass : String)
case class FindByName(name : String)
case class FindByEmail(email : String)

object User extends DefaultJsonProtocol with SprayJsonSupport {
  implicit val userFormat = jsonFormat3(User.apply)
}

object FindByName extends DefaultJsonProtocol with SprayJsonSupport{
  implicit val findByNameFormat = jsonFormat1(FindByName.apply)
}

object FindByEmail extends DefaultJsonProtocol with SprayJsonSupport {
  implicit val findByEmailFormat = jsonFormat1(FindByEmail.apply)
}