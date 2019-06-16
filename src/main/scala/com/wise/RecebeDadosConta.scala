package com.wise


import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010._
import org.apache.spark.streaming.{Seconds, StreamingContext}

object RecebeDadosConta extends App {

  val sparkConfig =
    new SparkConf().setMaster("local[*]").setAppName("dadosConta")

  val sparkStreamingContext = new StreamingContext(sparkConfig, Seconds(10))

  val kafkaConfig = Map[String, Object](
    "bootstrap.servers" -> "localhost:9092",
    "key.deserializer" -> classOf[StringDeserializer],
    "value.deserializer" -> classOf[StringDeserializer],
    "group.id" -> "conta",
    "auto.offset.reset" -> "latest",
    "enable.auto.commit" -> (false: java.lang.Boolean)
  )

  val kafkaTopics = Array("first_topic")
  val stream = KafkaUtils.createDirectStream[String, String](
    sparkStreamingContext,
    PreferConsistent,
    Subscribe[String, String](kafkaTopics, kafkaConfig)
  )

  stream.foreachRDD(a => {
      a.foreach(x => {
          val row : Array[String] = x.value().split(";")
          val tupleDados = (row(0), row(1), row(2))
          val key = tupleDados._2.reverse + tupleDados._1.reverse
          val valor = tupleDados._3.toDouble
          println(key + " -> " + valor.toString)
        }
      )
    }
  )

  // Start Stream
  sparkStreamingContext.start()
  sparkStreamingContext.awaitTermination()

}
