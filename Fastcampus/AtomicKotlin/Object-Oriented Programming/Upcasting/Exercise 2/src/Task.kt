// Upcasting/Task2.kt
package upcastingExercise2
import atomictest.eq

interface Apple {
  fun consume(): String
}

class GrannySmith

class Gala

class Fuji

class Braeburn

fun main() {
  val apples = listOf(
    GrannySmith(),
    Gala(),
    Fuji(),
    Braeburn()
  )
  apples.map { TODO() } eq
    "[chomp GrannySmith, " +
    "bite Gala, press Fuji, peel Braeburn]"
}