package com.example.repository

import com.example.config.MongoConnection
import com.example.domain.User
import com.example.controller.transformer.UserTransformer
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

  def findAllUsers : MongoCursor = collection.collection.find()

  def findUserByName (name : String) = {
    lazy val query = MongoDBObject("name" -> name)
    lazy val result = collection.collection.findOne(query)
    result
  }

  def findUserByEmail (email : String) : MongoCursor = {
    lazy val query = MongoDBObject("email" -> email)
    lazy val result = collection.collection.find(query)
    result
  }
}
