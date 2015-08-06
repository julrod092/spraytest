package com.example.controller

import com.example.domain.{User, UserLogin}
import com.example.repository.UserDAO
import com.mongodb.casbah.Imports._
import spray.http.{HttpResponse, StatusCodes}

class UserController {

  private val userDAO = new UserDAO

  def registerUser(user: User): HttpResponse = {
    val create = userDAO.createUser(user)
    create
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

