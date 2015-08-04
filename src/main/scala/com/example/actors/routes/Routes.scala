package com.example.actors.routes

import akka.actor.Actor
import com.example.controller.ControllerUser
import com.example.domain.User
import spray.http.MediaTypes._
import spray.http._
import spray.httpx.SprayJsonSupport
import spray.routing._

class RoutesActor extends Actor with RoutesTrait {
  def actorRefFactory = context

  def receive = runRoute(userRoute ~ staticRoute)
}

trait RoutesTrait extends HttpService with SprayJsonSupport {

  val userController = new ControllerUser

  val userRoute =
    path("user"){
      put {
        putRoute
      } ~
      get {
        getRoutes
      }
    }

  protected lazy val putRoute =
    entity(as[User]) { user =>
      detach() {
        if (userController.registerUser(user)){
          complete{
            HttpResponse(StatusCodes.OK)
          }
        }else{
          complete{
            HttpResponse(StatusCodes.BadRequest)
          }
        }
      }
    }

  protected lazy val getRoutes =
    parameter('email.as[String]) { email =>
      detach(){
        val login = userController.loginUser(email)
        respondWithMediaType(`application/json`) {
          complete{
            login.toString()
          }
        }
      }
    }

  def staticRoute: Route =
    path("")(getFromResource("webapp/index.html")) ~ getFromResourceDirectory("webapp")
}