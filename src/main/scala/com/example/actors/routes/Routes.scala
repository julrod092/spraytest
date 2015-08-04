package com.example.actors.routes

import akka.actor.Actor
import com.example.controller.ControllerUser
import com.example.domain.{User, UserLogin}
import spray.http._
import spray.httpx.SprayJsonSupport
import spray.routing._

class RoutesActor extends Actor with RoutesTrait {
  def actorRefFactory = context

  def receive = runRoute(myRoute ~ staticRoute)
}

trait RoutesTrait extends HttpService with SprayJsonSupport {

  val userController = new ControllerUser

  val myRoute =
    path("user") {
      put {
        putRoute
      } ~
        post {
          login
        }
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

  protected lazy val login =
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

  def staticRoute: Route =
    path("")(getFromResource("webapp/index.html")) ~ getFromResourceDirectory("webapp")
}