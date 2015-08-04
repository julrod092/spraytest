package com.example.config

import akka.actor.ActorSystem
import com.example.actors.routes.{ApiRouteActor, RoutesActor}

/**
 * Created by julian.rodriguez on 04/08/2015.
 */

object ActorSystemBean {
  def apply(): ActorSystemBean = new ActorSystemBean
}

class ActorSystemBean {

  implicit val system = ActorSystem("FruitStore")

  lazy val userService = system.actorOf(RoutesActor.props, "user-router")

  lazy val apiRouteActor = system.actorOf(ApiRouteActor.props(userService), "Api-Route-Actor")
}
