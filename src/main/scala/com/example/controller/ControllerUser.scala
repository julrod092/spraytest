package com.example.controller

import com.example.domain.{User, UserLogin}
import com.example.repository.UserDAO
import com.mongodb.casbah.Imports._

class ControllerUser {

  private val userDAO = new UserDAO

  def registerUser(user: User): Boolean = {
    val create = userDAO.createUser(user)
    if (create) {
      true
    } else {
      false
    }
  }

  def loginUser(userLogin: UserLogin):Boolean = {
    val findByName = userDAO.findUserByEmail(userLogin)
    try{
      val nameQuery = findByName.map(_.as[String]("email"))
      val passQuery = findByName.map(_.as[String]("pass"))
      val Some(email) = nameQuery
      val Some(pass) = passQuery
      if(userLogin.email==email && userLogin.pass==pass ){
        true
      }else{
        false
      }
    }catch{
      case e:MatchError => false
    }
  }
}

