package com.example.domain

import spray.httpx.SprayJsonSupport
import spray.json.DefaultJsonProtocol

case class User (name : String, email : String, pass : String)
case class UserLogin(email : String, pass : String)
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

object UserLogin extends DefaultJsonProtocol with SprayJsonSupport {
  implicit val userLoginFormat = jsonFormat2(UserLogin.apply)
}