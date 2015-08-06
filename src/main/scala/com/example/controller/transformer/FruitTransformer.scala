package com.example.controller.transformer

import com.example.domain.Fruit
import com.mongodb.casbah.commons.MongoDBObject

class FruitTransformer {
  def mongoDBObject (fruit: Fruit) = MongoDBObject(
    "name" -> fruit.name,
    "peso" -> fruit.peso,
    "quantity" -> fruit.quantity,
    "image" -> fruit.image
  )
}
