// ScopeFunctions/Task3.kt
package scopeFunctionsExercise3
import atomictest.eq
import scopeFunctionsExercise1.NumPair

class Math {
  fun np(x: Int, y: Int) = NumPair(x, y)
  fun add() = 1000
  fun subtract() = -1000
  fun NumPair.calc() = multiply()
}

fun main() {
  TODO()
}