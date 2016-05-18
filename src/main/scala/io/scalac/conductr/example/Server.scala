package io.scalac.conductr.example

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Sink, Source}

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.language.postfixOps

object Server extends App {

  implicit val actorSystem = ActorSystem("microservice-as")
  implicit val flowMaterializer = ActorMaterializer()

  val httpService = Http()

  val HOST = "127.0.0.1"
  val PORT = 8080

  val serverSource: Source[Http.IncomingConnection, Future[Http.ServerBinding]] =
    httpService.bind(interface = HOST, PORT)

  val binding: Future[Http.ServerBinding] =
    serverSource.to(Sink.foreach { connection =>
      println("Accepted new connection from {}", connection.remoteAddress)
      connection handleWith route
    }).run()

  println(s"Server is now online at http://$HOST:$PORT\n")




  //shutdown Hook
  import actorSystem.dispatcher

  scala.sys.addShutdownHook {
    println("Terminating...")
    binding
      .flatMap(_.unbind())
      .onComplete { _ =>
        flowMaterializer.shutdown()
        actorSystem.terminate()
      }
    Await.result(actorSystem.whenTerminated, 60 seconds)
    println("Terminated... Bye")
  }

  //basic route
  def route = complete {
    "Welcome to microservice"
  }
}
