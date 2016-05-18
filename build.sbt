name := "Scalac-ConductR-example-microservice"

version := "1.0.0"

organization := "io.scalac"

scalaVersion := "2.11.7"

resolvers += Resolver.bintrayRepo("typesafe", "maven-releases")

libraryDependencies ++= {
  val akkaV = "2.4.5"
  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaV,
    "com.typesafe.akka" %% "akka-slf4j" % akkaV,
    "com.typesafe.akka" %% "akka-http-experimental" % akkaV,
    "com.typesafe.akka" %% "akka-http-spray-json-experimental" % akkaV
    //  "com.typesafe.conductr" %% "scala-conductr-bundle-lib" % "1.4.3"
  )
}
