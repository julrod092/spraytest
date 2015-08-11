package com.example.config

import com.mongodb.casbah.Imports._
import com.typesafe.config.ConfigFactory

class MongoConnection (name : String)  {

  val conf = ConfigFactory.load()
  val dbconf = conf.getConfig("dbConfiguration")

  lazy private val mongoClient = MongoClient(dbconf.getString("dbHost"), dbconf.getInt("dbPort"))
  lazy private val mongoDB = mongoClient(dbconf.getString("dbName"))

  def collection : MongoCollection = {
    lazy val collection : MongoCollection = mongoDB(name)
    collection
  }
}
