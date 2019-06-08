package com.wise


object Hello extends App {
  // Factory method
  val city = new HelloCity("a")
  city.sayHelloToCity
}