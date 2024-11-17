// ExceptionHandling/Task1.kt
package exceptionHandlingExercise1
import atomictest.trace
import kotlin.random.Random

data class Hamster(val name: String)

// TODO exception classes

class HamsterCage(val capacity: Int) {
  private val rand = Random(47)
  private val cage = HashSet<Hamster>()
  // TODO
}

val hamsters = listOf(
  Hamster("Sally"), Hamster("Ralph"),
  Hamster("Bob"), Hamster("Sergio"),
  Hamster("Allison"), Hamster("Jane"))

fun test(hc: HamsterCage) {
  // TODO
}

fun main() {
  test(HamsterCage(3))
  trace eq """
    Hamster(name=Sally)
    Hamster(name=Ralph)
    Hamster(name=Bob)
    Hamster(name=Sergio)
    HamsterCageException: Cage full > 3
    Hamster(name=Sally)
    HamsterCageException: No Hamster Morty
    Feeding complete
    HamsterCageException: Cage out of water
  """
}