package com.example.domain

import spray.httpx.SprayJsonSupport
import spray.json

/**
 * Created by sebastian.garces on 04/08/2015.
 */
case class Fruit (name : String, peso: Int, color: String)

object Fruit extends json.DefaultJsonProtocol with SprayJsonSupport {
  implicit val userLoginFormat = jsonFormat3(Fruit.apply)
}