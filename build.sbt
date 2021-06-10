enablePlugins(ScalaNativePlugin)

lazy val root = (project in file("."))
  .settings(
    organization := "io.github.rpiotrow",
    name := "git-branch-for-jira-task",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "2.13.5",
    libraryDependencies ++= Seq(
      "com.github.scopt" %%% "scopt" % "4.0.1",
      "org.scalameta"    %%  "munit" % "0.7.3" % Test
    ),
  )

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-Xfatal-warnings",
)
