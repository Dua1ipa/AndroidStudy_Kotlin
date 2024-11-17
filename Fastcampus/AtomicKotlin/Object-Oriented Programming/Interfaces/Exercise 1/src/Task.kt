// Interfaces/Task1.kt
package interfacesExercise1
import atomictest.eq

interface Computer {
  fun calculateAnswer(): Int
}

class Cloud

data class Node(
  private val result: Int
)

fun main() {
  // Cloud(10).calculateAnswer() eq 45
}