package com.wise

class HelloCity(CityName: String) {
  def sayHelloToCity = println("Hello, " + CityName + "!")
}

object HelloCity {
  // Factory method
  def apply(CityName: String) = new HelloCity(CityName)
}

