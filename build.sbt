organization  := "com.example"

version       := "0.1"

scalaVersion  := "2.10.5"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

resolvers ++= Seq(
  "Typesafe repository releases" at "http://repo.typesafe.com/typesafe/releases/"
)

libraryDependencies ++= {
  val akkaV = "2.3.9"
  val sprayV = "1.3.3"
  Seq(
    "io.spray"            %%  "spray-can"     % sprayV,
    "io.spray"            %%  "spray-routing" % sprayV,
    "io.spray"            %%  "spray-testkit" % sprayV  % "test",
    "com.typesafe.akka"   %%  "akka-actor"    % akkaV,
    "com.typesafe.akka"   %%  "akka-testkit"  % akkaV   % "test",
    "org.specs2"          %%  "specs2-core"   % "2.3.7" % "test",
    "org.mongodb"         %% "casbah"         % "2.8.2",
    "io.spray"            %%  "spray-json"    % "1.3.2",
    "org.json4s"          %% "json4s-native"  % "3.2.11"
  )
}

Revolver.settings
