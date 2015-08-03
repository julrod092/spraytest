package com.example.controller.transformer

/**
 * Created by julian.rodriguez on 30/07/2015.
 */
import com.example.domain.User
import com.mongodb.casbah.commons.MongoDBObject

class UserTransformer {

  def mongoDBObject (user : User) = MongoDBObject(
    "name" -> user.name,
    "email" -> user.email,
    "pass" -> user.pass
  )

}
