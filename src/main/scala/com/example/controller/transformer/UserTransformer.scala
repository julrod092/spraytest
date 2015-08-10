package com.example.controller.transformer

import com.example.domain.User
import com.mongodb.casbah.commons.MongoDBObject

class UserTransformer {

  def mongoDBObject (user : User) = MongoDBObject(
    "name" -> user.name,
    "email" -> user.email,
    "pass" -> user.pass
  )

}
