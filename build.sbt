organization := "com.wise"
version      := "0.0.1"
scalaVersion := "2.12.8"
name := "dadosConta"

lazy val dadosConta = (project in file("."))
  .settings(
    libraryDependencies ++= List(
      "org.scalatest" %% "scalatest" % "3.0.5" % Test,
      "org.apache.spark" %% "spark-streaming-kafka-0-10" % "2.4.3",
      "org.apache.hbase" % "hbase-client" % "1.4.9" ,
      "org.apache.hbase" % "hbase-common" % "1.4.9" ,
      "org.apache.hbase" % "hbase-server" % "1.4.9",
      "org.apache.spark" %% "spark-core" % "2.4.3" ,
      "org.apache.spark" %% "spark-streaming-kinesis-asl" % "2.4.3",
      "org.apache.spark" %% "spark-streaming" % "2.4.3" ,
      "org.apache.spark" %% "spark-sql" % "2.4.3",
      "com.github.seratch" %% "awscala-s3" % "0.8.+"
    )
  )