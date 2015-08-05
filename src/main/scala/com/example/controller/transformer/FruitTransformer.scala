package com.example.controller.transformer

import com.example.domain.Fruit
import com.mongodb.casbah.commons.MongoDBObject

/**
 * Created by sebastian.garces on 05/08/2015.
 */
class FruitTransformer {
  def mongoDBObject (fruit: Fruit) = MongoDBObject(
    "name" -> fruit.name,
    "peso" -> fruit.peso,
    "color" -> fruit.color
  )
}
