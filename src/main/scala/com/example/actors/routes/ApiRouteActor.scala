package com.example.actors.routes

import akka.actor.{ActorLogging, Props, ActorRef, Actor}
import spray.routing.HttpService

/**
 * Created by julian.rodriguez on 04/08/2015.
 */

object ApiRouteActor {
  def props (route1 : ActorRef, route2 : ActorRef) : Props = Props(new ApiRouteActor(route1, route2))
}

class ApiRouteActor (route1 : ActorRef, route2 : ActorRef) extends Actor
with HttpService
with ActorLogging {
  println(route1, route2)
  def actorRefFactory = context
  def receive = runRoute{
    compressResponseIfRequested() {
      pathPrefix("user"){
        ctx => route1 ! ctx
      } ~
      pathPrefix("fruit"){
        ctx => route2 ! ctx
      }
    }~
    compressResponseIfRequested(){
      path("")(getFromResource("webapp/index.html")) ~ getFromResourceDirectory("webapp")
    }
  }
}