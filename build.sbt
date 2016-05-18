name := "conductr-microservice"

version := "1.0.0"

organization := "io.scalac"

scalaVersion := "2.11.8"

libraryDependencies ++= {
  val akkaV = "2.4.5"
  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaV,
    "com.typesafe.akka" %% "akka-slf4j" % akkaV,
    "com.typesafe.akka" %% "akka-http-experimental" % akkaV,
    "com.typesafe.akka" %% "akka-http-spray-json-experimental" % akkaV
  )
}

lazy val root = project in file(".")

