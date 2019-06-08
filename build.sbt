ThisBuild / organization := "com.wise"
ThisBuild / version      := "0.0.1"
ThisBuild / scalaVersion := "2.12.8"

lazy val hello = (project in file("."))
  .settings(
    name := "dadosConta",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test,
  )