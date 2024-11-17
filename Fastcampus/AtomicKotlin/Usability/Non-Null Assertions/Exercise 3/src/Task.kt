// NonNullAssertions/Task3.kt
package nonNullAssertionsExercise3
import atomictest.eq
import kotlin.math.absoluteValue

fun absMinIsMax(list: List<Int>): Boolean {
  TODO()
}

fun main() {
  absMinIsMax(listOf(-10, 1, 10)) eq true
  absMinIsMax(listOf(10, 10)) eq true
  absMinIsMax(listOf(-10, 1)) eq false
}