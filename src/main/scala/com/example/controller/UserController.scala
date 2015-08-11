package com.example.controller

import com.example.domain.{User, UserLogin}
import com.example.repository.UserDAO
import com.mongodb.casbah.Imports._
import spray.http.{HttpResponse, StatusCodes}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success}

class UserController {
  private val userDAO = new UserDAO
  private val mail = new MailController

  def registerUser(user: User) = {
    val create = userDAO.createUser(user)
    create match {
      case true =>
        completeSendEmail(user.email)
        HttpResponse(StatusCodes.OK, "User correctly registration")
      case false => HttpResponse(StatusCodes.BadRequest, "Wrong Registration")
    }
  }

  def completeSendEmail (email : String): Unit ={
    val future = Future{mail.sendMail(email, "Test", "Test")}
    future onComplete{
      case Success(_) => println("Email correctly send")
      case Failure(_) => completeSendEmail(email)
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

