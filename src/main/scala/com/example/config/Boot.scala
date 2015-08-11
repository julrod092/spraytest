package com.example.config

import akka.io.IO
import akka.pattern.ask
import akka.util.Timeout
import com.typesafe.config.ConfigFactory
import spray.can.Http

import scala.concurrent.duration._


object Boot extends App {

  val services = ActorSystemBean()
  implicit val system = services.system
  val service = services.apiRouteActor

  implicit val timeout = Timeout(5.seconds)

  val conf = ConfigFactory.load()
  val server = conf.getConfig("server")

  IO(Http) ? Http.Bind(service, interface = server.getString("host"), port = server.getInt("port"))
}
