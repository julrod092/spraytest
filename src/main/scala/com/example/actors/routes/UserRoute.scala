package com.example.actors.routes

import akka.actor.{Actor, Props}
import com.example.controller.ControllerUser
import com.example.domain.{User, UserLogin}
import spray.http._
import spray.httpx.SprayJsonSupport
import spray.routing._

object UserRouteActor{
  def props : Props = Props(new UserRouteActor)
}

class UserRouteActor extends Actor with UserRouteTrait {
  def actorRefFactory = context
  def receive = runRoute(userRoute)
}

trait UserRouteTrait extends HttpService with SprayJsonSupport {

  val userController = new ControllerUser

  val userRoute =
    put {
      putRoute
    } ~
    post {
      postRoute
    }

  protected lazy val putRoute =
    entity(as[User]) { user =>
      detach() {
        if (userController.registerUser(user)) {
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

  protected lazy val postRoute =
    entity(as[UserLogin]) { userLogin =>
      detach() {
        if (userController.loginUser(userLogin)) {
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