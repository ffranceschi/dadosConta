package com.wise


object Hello extends App {
  // Factory method
  val city = HelloCity.apply("ab")
  city.sayHelloToCity

}