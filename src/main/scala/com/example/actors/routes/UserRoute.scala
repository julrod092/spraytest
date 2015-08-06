package com.example.actors.routes

import akka.actor.{Actor, Props}
import com.example.controller.UserController
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

  val userController = new UserController

  val userRoute =
    put {
      putRoute
    } ~
    post {
      postRoute
    }

  protected lazy val putRoute =
    entity(as[User]) { user =>
      val create = userController.registerUser(user)
      detach() {
        complete{
          create
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