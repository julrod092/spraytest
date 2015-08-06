package com.example.repository

import com.example.config.MongoConnection
import com.example.controller.transformer.UserTransformer
import com.example.domain.{User, UserLogin}
import com.mongodb.casbah.MongoCursor
import com.mongodb.casbah.commons.MongoDBObject
import com.mongodb.{BasicDBObject, MongoException, MongoExecutionTimeoutException}
import spray.http.{HttpResponse, StatusCodes}

class UserDAO {

  lazy val collection = new MongoConnection("user")

  def createUser (user : User) : HttpResponse = {
    val userMongoObject = new UserTransformer
    try{
      collection.collection.createIndex(new BasicDBObject("email", 1), new BasicDBObject("unique", true))
      collection.collection.insert(userMongoObject.mongoDBObject(user))
      HttpResponse(StatusCodes.OK, "Creacion correcta")
    }catch{
      case t : MongoExecutionTimeoutException => HttpResponse(StatusCodes.BadRequest, "MongDB esta apagado")
      case t : MongoException => HttpResponse(StatusCodes.BadRequest, "Usuario ya existe")
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
