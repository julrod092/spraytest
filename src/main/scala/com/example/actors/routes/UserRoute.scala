package com.example.actors.routes

import akka.actor.{ Actor, Props }
import com.example.controller.UserController
import com.example.domain.{ User, UserLogin }
import spray.httpx.SprayJsonSupport
import spray.routing._
import spray.http.HttpHeaders
import com.example.actors.email.EmailActor
import scala.concurrent.ExecutionContext.Implicits.global._

object UserRouteActor {
  def props: Props = Props(new UserRouteActor)

}

class UserRouteActor extends Actor with HttpService with SprayJsonSupport {

  val AccessControlAllowAll = HttpHeaders.RawHeader(
    "Access-Control-Allow-Origin", "*")
  val AccessControlAllowHeadersAll = HttpHeaders.RawHeader(
    "Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept")
  def actorRefFactory = context
  val emailActor = context.actorOf(Props[EmailActor])
  def receive = runRoute(userRoute)

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
        complete {
          //emailActor ! user
          create
        }
      }

    }

  protected lazy val postRoute =
    entity(as[UserLogin]) { userLogin =>
      respondWithHeaders(AccessControlAllowAll, AccessControlAllowHeadersAll) {
        detach() {
          val login = userController.loginUser(userLogin)
          complete {
            login

          }
        }
      }
    }
}

