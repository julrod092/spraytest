package com.example.controller

import com.example.domain.{UserLogin, User}
import com.example.repository.UserDAO
import com.mongodb.casbah.Imports._

import scala.util.parsing.json.JSONArray

/**
 * Created by julian.rodriguez on 03/08/2015.
 */

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
    val userDAO = new UserDAO
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
