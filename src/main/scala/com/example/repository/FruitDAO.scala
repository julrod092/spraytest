package com.example.repository

import com.example.config.MongoConnection
import com.example.controller.transformer.FruitTransformer
import com.example.domain.Fruit
import com.mongodb.casbah.commons.MongoDBObject
import com.mongodb.{BasicDBObject, MongoExecutionTimeoutException}
import com.mongodb.casbah.MongoCursor
import spray.http.{StatusCodes, HttpResponse}

class FruitDAO {
  lazy val collection = new MongoConnection("fruit")

  def addFruit (fruit: Fruit) : HttpResponse = {
    val fruitMongoObject = new FruitTransformer
    try{
      collection.collection.insert(fruitMongoObject.mongoDBObject(fruit))
      HttpResponse(StatusCodes.OK, "Creacion correcta")
    }catch{
      case t : MongoExecutionTimeoutException => HttpResponse(StatusCodes.BadRequest, "MongDB esta apagado")
    }
  }

  def getAllFruits : MongoCursor = {
    val listOfFruits : MongoCursor = collection.collection.find()
    listOfFruits
  }
}