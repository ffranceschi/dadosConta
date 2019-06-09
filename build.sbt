organization := "com.wise"
version      := "0.0.1"
scalaVersion := "2.12.8"

lazy val dadosConta = (project in file("."))
  .settings(
    name := "dadosConta",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test,
  )