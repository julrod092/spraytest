package com.example.actors.routes

import akka.actor.{ Actor, Props }
import com.example.controller.FruitController
import com.example.domain.Fruit
import spray.httpx.SprayJsonSupport
import spray.routing.HttpService

object FruitRouteActor {
  def props: Props = Props(new FruitRouteActor)
}

class FruitRouteActor extends Actor with FruitRouteTrait {
  def actorRefFactory = context

  def receive = runRoute(fruitRoute)
}

trait FruitRouteTrait extends HttpService with SprayJsonSupport {

  val fruitController = new FruitController

  val fruitRoute =
    put {
      putRoute
    } ~
      get {
        getRoute
      } ~
      post {
        postRoute
      }

  protected lazy val putRoute =
    entity(as[Fruit]) { fruit =>
      val response = fruitController.addFruit(fruit)
      detach() {
        complete {
          response
        }
      }
    }

  protected lazy val getRoute =
    path("getAllFruits") {
      detach() {
        val fruitController = new FruitController
        complete {
          val getFruits = fruitController.getAllFruits
          getFruits
        }
      }
    }

  protected lazy val postRoute =
    path("buyFruit") {
      detach() {
        entity(as[Fruit]) {fruit=>
          val fruitController = new FruitController
          complete {
            val sellFrut = fruitController.sellFruit(fruit)
            sellFrut
          }
        }
      }
    }
}
