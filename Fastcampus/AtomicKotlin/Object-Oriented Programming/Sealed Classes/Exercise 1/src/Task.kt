// SealedClasses/SealedEx1.kt
package sealedClassesExercise1
import atomictest.*

sealed class Transport {
  // val capacity
}

data class Train(
  val line: String,
  val capacity: Int
) : Transport()

data class Bus(
  val number: String,
  val capacity: Int
) : Transport()

// class Tram

// class Plane

fun travel(transport: Transport) {
  when (transport) {
    is Train ->
      trace("Train ${transport.line}")
    is Bus ->
      trace("Bus ${transport.number}")
    // TODO
  }
  // TODO
}

fun main() {
  listOf(
    Train("S1", 200),
    Bus("11", 45),
    // TODO
  ).forEach(::travel)
  trace eq """
    Train S1
    size 200
    Bus 11
    size 45
    Tram 22A Express
    size 60
    Plane 1103
    size 190
  """
}