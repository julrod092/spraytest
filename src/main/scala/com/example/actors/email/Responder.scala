package com.example.actors.email

import akka.actor.Props
import com.example.controller.UserController
import spray.routing.RequestContext
import akka.actor.PoisonPill
import akka.actor.Actor
import com.example.domain.{User,UserLogin}

/**
 * @author luis.saldarriaga
 */
class Responder(requestContext: RequestContext) extends Actor {
  val emailActor = context.actorOf(Props[EmailActor])
  val userController = new UserController
  def receive = {

    case user: User =>
      val create = userController.registerUser(user)
      emailActor ! user
      requestContext.complete(create)
      killYourself
      
    case userLogin:UserLogin => 
      val login = userController.loginUser(userLogin)
      requestContext.complete(login)
      killYourself
  }

  private def killYourself = self ! PoisonPill

}