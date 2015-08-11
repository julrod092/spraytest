package com.example.config

import akka.io.IO
import akka.pattern.ask
import akka.util.Timeout
import spray.can.Http
import scala.concurrent.duration._

object Boot extends App {

  val services = ActorSystemBean()
  implicit val system = services.system
  val service = services.apiRouteActor

  implicit val timeout = Timeout(5.seconds)

  IO(Http) ? Http.Bind(service, interface = "localhost", port = 8888)
}
