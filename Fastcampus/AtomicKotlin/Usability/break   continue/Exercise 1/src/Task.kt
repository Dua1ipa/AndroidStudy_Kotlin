// BreakAndContinue/Task1.kt
package breakAndContinueExercise1
import atomictest.*

fun readNumbers(vararg n: String) {
  TODO()
}

fun main() {
  readNumbers("1", "a", "3", "10", "0", "19")
  trace eq """
    Not a number: a
    Sum: 14
  """
}