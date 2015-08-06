package com.example.domain

import spray.httpx.SprayJsonSupport
import spray.json

case class Fruit (name : String, peso: Int, quantity: Int, image: String)

object Fruit extends json.DefaultJsonProtocol with SprayJsonSupport {
  implicit val userLoginFormat = jsonFormat4(Fruit.apply)
}