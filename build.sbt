lazy val itgm7 = project
  .copy(id = "itgm7")
  .in(file("."))
  .enablePlugins(AutomateHeaderPlugin, GitVersioning)

name := "itgm7"

libraryDependencies ++= Vector(
  Library.scalaCheck % "test"
)

initialCommands := """|import com.github.rockjam.itgm7._
                      |""".stripMargin
