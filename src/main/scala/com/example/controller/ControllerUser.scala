package com.example.controller

import com.example.domain.{FindByEmail, User}
import com.example.repository.UserDAO
import spray.json._

import scala.util.parsing.json.JSONArray

/**
 * Created by julian.rodriguez on 03/08/2015.
 */

class ControllerUser {

  private val userDAO = new UserDAO

  def registerUser(user : User) : Boolean = {
    val create = userDAO.createUser(user)
    if(create){
      true
    }else{
      false
    }
  }

  def loginUser(email : String) = {
    val userDAO = new UserDAO
    val findByName = userDAO.findUserByEmail(email)
    val array = JSONArray(findByName.toList)
    array
  }
}
