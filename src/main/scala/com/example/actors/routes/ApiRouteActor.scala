package com.example.actors.routes

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import spray.routing.HttpService

object ApiRouteActor {
  def props (userService : ActorRef, fruitService : ActorRef) : Props = Props(new ApiRouteActor(userService, fruitService))
}

class ApiRouteActor (userService : ActorRef, fruitService : ActorRef) extends Actor with HttpService with ActorLogging {

  def actorRefFactory = context
  def receive = runRoute{
    compressResponseIfRequested() {
      pathPrefix("user"){
        ctx => userService ! ctx
      } ~
        pathPrefix("fruit"){
          ctx => fruitService ! ctx
        }
    }~
      compressResponseIfRequested(){
        path("")(getFromResource("webapp/index.html")) ~ getFromResourceDirectory("webapp")
      }
  }
}