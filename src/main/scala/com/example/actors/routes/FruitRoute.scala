package com.example.actors.routes

import akka.actor.{Actor, Props}
import com.example.controller.FruitController
import com.example.domain.{Fruit, User}
import spray.http.{StatusCodes, HttpResponse}
import spray.httpx.SprayJsonSupport
import spray.routing.HttpService

/**
 * Created by julian.rodriguez on 04/08/2015.
 */

object FruitRouteActor{
  def props : Props = Props(new UserRouteActor)
}

class FruitRoute extends Actor with FruitRouteTrait{
  def actorRefFactory = context
  def receive = runRoute(fruitRoute)
}

trait FruitRouteTrait extends HttpService with SprayJsonSupport {

  val fruitController = new FruitController

  val fruitRoute =
    put {
      putRoute
    }

  protected lazy val putRoute =
    entity(as[Fruit]) { fruit =>
      detach() {
        if (fruitController.addFruit(fruit)) {
          complete {
            HttpResponse(StatusCodes.OK)
          }
        } else {
          complete {
            HttpResponse(StatusCodes.BadRequest)
          }
        }
      }
    }
}
