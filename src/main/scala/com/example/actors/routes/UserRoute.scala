package com.example.actors.routes

import akka.actor.{ Actor, Props }
import com.example.controller.UserController
import com.example.domain.{ User, UserLogin }
import spray.httpx.SprayJsonSupport
import spray.routing._
import spray.http.HttpHeaders
import com.example.actors.email.EmailActor
import scala.concurrent.ExecutionContext.Implicits.global._
import akka.actor.ActorLogging
import spray.http.StatusCodes
import akka.actor.PoisonPill

object UserRouteActor {
  def props: Props = Props(new UserRouteActor)
}

class UserRouteActor extends Actor with UserRouteTrait {
  def actorRefFactory = context

  def receive = runRoute(userRoute)
}

trait UserRouteTrait extends HttpService with SprayJsonSupport { actor: Actor =>

  val userRoute =
    put {
      putRoute
    } ~
      post {
        postRoute
      }

  protected lazy val putRoute =
    entity(as[User]) { user =>

      detach() { requestContext =>
        val responder = createResponder(requestContext)
        responder ! user
      }

    }

  protected lazy val postRoute =
    entity(as[UserLogin]) { userLogin =>

      detach() { requestContext =>
        val responder = createResponder(requestContext)
        responder ! userLogin
      }

    }
  
  import com.example.actors.email.Responder
  private def createResponder(requestContext: RequestContext) =
    context.actorOf(Props(new Responder(requestContext)))
}

