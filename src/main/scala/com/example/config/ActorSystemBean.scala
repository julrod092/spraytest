package com.example.config

import akka.actor.ActorSystem
import com.example.actors.routes.{ApiRouteActor, FruitRouteActor, UserRouteActor}

object ActorSystemBean {
  def apply(): ActorSystemBean = new ActorSystemBean
}

class ActorSystemBean {

  implicit val system = ActorSystem("FruitStore")

  val fruitService = system.actorOf(FruitRouteActor.props, "fruit-router")
  val userService = system.actorOf(UserRouteActor.props, "user-router")

  val apiRouteActor = system.actorOf(ApiRouteActor.props(userService, fruitService), "Api-Route-Actor")
}
