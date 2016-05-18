import com.lightbend.conductr.sbt.BundleImport.ByteConversions._

name := "conductr-microservice"

version := "1.0.0"

organization := "io.scalac"

scalaVersion := "2.11.8"

resolvers += Resolver.bintrayRepo("typesafe", "maven-releases")

libraryDependencies ++= {
  val akkaV = "2.4.5"
  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaV,
    "com.typesafe.akka" %% "akka-slf4j" % akkaV,
    "com.typesafe.akka" %% "akka-http-experimental" % akkaV,
    "com.typesafe.akka" %% "akka-http-spray-json-experimental" % akkaV,
    "com.typesafe.conductr" %% "akka24-conductr-bundle-lib" % "1.4.3"
  )
}

BundleKeys.nrOfCpus := 1.0
BundleKeys.memory := 64.MiB
BundleKeys.diskSpace := 5.MiB
BundleKeys.roles := Set("backend")
BundleKeys.endpoints := Map("microservice" -> Endpoint("http", 0, services = Set(URI("http://:9000"))))

lazy val root = (project in file("."))
  .enablePlugins(JavaAppPackaging)
