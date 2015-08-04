package com.example.actors.routes

import akka.actor.{ActorLogging, Props, ActorRef, Actor}
import spray.routing.HttpService

/**
 * Created by julian.rodriguez on 04/08/2015.
 */

object ApiRouteActor {
  def props (route : ActorRef) : Props = Props(new ApiRouteActor(route))
}

class ApiRouteActor (route : ActorRef) extends Actor
with HttpService
with ActorLogging {

  def actorRefFactory = context
  def receive = runRoute{
    compressResponseIfRequested() {
      pathPrefix("user"){
        ctx => route ! ctx
      } ~
        path("")(getFromResource("webapp/index.html")) ~ getFromResourceDirectory("webapp")
    }
  }
}
