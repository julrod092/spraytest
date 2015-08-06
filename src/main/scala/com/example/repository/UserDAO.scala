package com.example.repository

import com.example.config.MongoConnection
import com.example.controller.transformer.UserTransformer
import com.example.domain.{User, UserLogin}
import com.mongodb.MongoExecutionTimeoutException
import com.mongodb.casbah.MongoCursor
import com.mongodb.casbah.commons.MongoDBObject

class UserDAO {

  lazy val collection = new MongoConnection("user")

  def createUser (user : User) : Boolean = {
    val userMongoObject = new UserTransformer
    try{
      collection.collection.insert(userMongoObject.mongoDBObject(user))
      true
    }catch{
      case t : MongoExecutionTimeoutException => false
    }
  }

  def getAllUsers : MongoCursor = {
    val getAllUsers = collection.collection.find()
    println(getAllUsers)
    getAllUsers
  }

  def findUserByEmail (userLogin: UserLogin) = {
    lazy val query = MongoDBObject("email" -> userLogin.email)
    lazy val result = collection.collection.findOne(query)
    result
  }
}
