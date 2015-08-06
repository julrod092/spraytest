package com.example.repository

import com.example.config.MongoConnection
import com.example.controller.transformer.FruitTransformer
import com.example.domain.Fruit
import com.mongodb.casbah.commons.MongoDBObject
import com.mongodb.{BasicDBObject, MongoExecutionTimeoutException}
import com.mongodb.casbah.MongoCursor

class FruitDAO {
  lazy val collection = new MongoConnection("fruit")

  def addFruit (fruit: Fruit) : Boolean = {
    val fruitMongoObject = new FruitTransformer
    try{
      collection.collection.insert(fruitMongoObject.mongoDBObject(fruit))
      true
    }catch{
      case t : MongoExecutionTimeoutException => false
    }
  }

  def getAllFruits : MongoCursor = {
    val listOfFruits : MongoCursor = collection.collection.find()
    listOfFruits
  }
}