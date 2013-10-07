scalaVersion := "2.10.3"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-json" % "2.2.0",
  "net.databinder.dispatch" %% "dispatch-core" % "0.11.0",
  "org.slf4j" % "slf4j-nop" % "1.7.5",
  "com.typesafe.akka" %% "akka-actor" % "2.2.1",
  "com.netflix.rxjava" % "rxjava-scala" % "0.14.2"
)

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

