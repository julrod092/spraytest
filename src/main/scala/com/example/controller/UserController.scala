package com.example.controller

import akka.actor.ActorSystem
import com.example.domain.{User, UserLogin}
import com.example.repository.UserDAO
import com.mongodb.casbah.Imports._
import spray.http.{HttpResponse, StatusCodes}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

class UserController {
  private val userDAO = new UserDAO
  private val mail = new MailController

  def registerUser(user: User): HttpResponse = {
    val create = userDAO.createUser(user)
    create match {
      case true => mail.sendMail(user.email, "Test", "Test"); HttpResponse(StatusCodes.OK, "User correctly registration")
      case false => HttpResponse(StatusCodes.BadRequest, "Wrong Registration")
    }
  }

  def loginUser(userLogin: UserLogin) : HttpResponse = {
    val findByName = userDAO.findUserByEmail(userLogin)
    try{
      val nameQuery = findByName.map(_.as[String]("email"))
      val passQuery = findByName.map(_.as[String]("pass"))
      val Some(email) = nameQuery
      val Some(pass) = passQuery
      if(userLogin.email == email){
        if(userLogin.pass == pass){
          HttpResponse(StatusCodes.OK, "Correct login")
        }else{
          HttpResponse(StatusCodes.NotAcceptable, "Incorrect Password")
        }
      }else{
        HttpResponse(StatusCodes.NotFound, "Incorrect email")
      }
    }catch{
      case e:MatchError => HttpResponse(StatusCodes.NotFound, "Incorrect email")
    }
  }
}

