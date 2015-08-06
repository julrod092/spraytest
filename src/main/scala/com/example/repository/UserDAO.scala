package com.example.repository

import com.example.config.MongoConnection
import com.example.controller.transformer.UserTransformer
import com.example.domain.{UserLogin, User}
import com.mongodb.{MongoException, BasicDBObject, MongoExecutionTimeoutException}
import com.mongodb.casbah.MongoCursor
import com.mongodb.casbah.commons.MongoDBObject

class UserDAO {

  lazy val collection = new MongoConnection("user")

  def createUser (user : User) : (Boolean,String) = {
    val userMongoObject = new UserTransformer
    try{
      collection.collection.createIndex(new BasicDBObject("email", 1), new BasicDBObject("unique", true))
      collection.collection.insert(userMongoObject.mongoDBObject(user))
      (true,"Creacion correcta")
    }catch{
      case t : MongoExecutionTimeoutException => (false,"MongDB esta apagado")
      case t : MongoException => (false,"Usuario ya existe")
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
