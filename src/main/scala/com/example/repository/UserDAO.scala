package com.example.repository

import com.example.config.MongoConnection
import com.example.controller.transformer.UserTransformer
import com.example.domain.{User, UserLogin}
import com.mongodb.casbah.MongoCursor
import com.mongodb.casbah.commons.MongoDBObject
import com.mongodb.{BasicDBObject, MongoException, MongoExecutionTimeoutException}

class UserDAO {

  lazy val collection = new MongoConnection("user")

  def createUser (user : User) : Boolean = {
    val userMongoObject = new UserTransformer
    try{
      collection.collection.createIndex(new BasicDBObject("email", 1), new BasicDBObject("unique", true))
      collection.collection.insert(userMongoObject.mongoDBObject(user))
      true
    }catch{
      case t : MongoExecutionTimeoutException => false
      case t : MongoException => false
    }
  }

  def findAllUsers : MongoCursor = collection.collection.find()

  def findUserByName (name : String) = {
    lazy val query = MongoDBObject("name" -> name)
    lazy val result = collection.collection.findOne(query)
    result
  }

  def findUserByEmail (userLogin: UserLogin) = {
    lazy val query = MongoDBObject("email" -> userLogin.email)
    lazy val result = collection.collection.findOne(query)
    result
  }
}
