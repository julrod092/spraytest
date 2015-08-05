package com.example.actors.routes

import akka.actor.{Actor, Props}
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

  val fruitRoute =
  get{
    complete{
      <p>Hola</p>
    }
  }
}
