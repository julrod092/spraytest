package com.example.config

import com.mongodb.casbah.Imports._

class MongoConnection (name : String)  {

  lazy private val mongoClient = MongoClient("localhost", 27017)
  lazy private val mongoDB = mongoClient("testSpray")

  def collection : MongoCollection = {
    lazy val collection : MongoCollection = mongoDB(name)
    collection
  }
}
