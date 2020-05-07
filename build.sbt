
enablePlugins(GraalVMNativeImagePlugin)

lazy val root = (project in file("."))
  .settings(
    organization := "io.github.rpiotrow",
    name := "git-branch-for-jira-task",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "2.13.1",
    libraryDependencies ++= Seq(
      "com.github.scopt" %% "scopt" % "3.7.1",
      "org.scalameta"    %% "munit" % "0.7.3" % Test
    ),
  )

Global / cancelable := false

scalacOptions ++= Seq(
  "-target:jvm-11",
  "-deprecation",
  "-encoding", "UTF-8",
  "-language:higherKinds",
  "-language:postfixOps",
  "-feature",
  "-Xfatal-warnings",
)

initialize := {
  val _ = initialize.value
  val javaVersion = sys.props("java.specification.version")
  if (javaVersion != "11")
    sys.error("Java 11 is required for this project. Found " + javaVersion + " instead")
}

publishArtifact in (Compile, packageDoc) := false

publishArtifact in packageDoc := false

sources in (Compile,doc) := Seq.empty

// if this is specified, graalvm runs inside docker, otherwise it uses an PATH'd native-image
graalVMNativeImageGraalVersion := Some("20.0.0-java11")

graalVMNativeImageOptions ++= Seq(
  "--verbose",
  "--no-server",
  "--no-fallback",
  "--static",
  "--report-unsupported-elements-at-runtime",
  "-H:+ReportExceptionStackTraces",
  "-H:+ReportUnsupportedElementsAtRuntime",
  "-H:+TraceClassInitialization",
  "-H:+PrintClassInitialization",
  "--initialize-at-build-time=scala.runtime.Statics$VM",
)
