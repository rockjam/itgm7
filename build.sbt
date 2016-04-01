lazy val itgm7 = project
  .copy(id = "itgm7")
  .in(file("."))
  .enablePlugins(AutomateHeaderPlugin, GitVersioning)

name := "itgm7"

libraryDependencies ++= Vector(
  "com.typesafe.akka" %% "akka-actor"   % "2.4.2",
  "io.spray"          %% "spray-client" % "1.3.3",
  Library.scalaCheck  %  "test"
)

initialCommands := """|import com.github.rockjam.itgm7._
                      |""".stripMargin
