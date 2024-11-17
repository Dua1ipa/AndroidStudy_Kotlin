// WhenExpressions/Task2.kt
package whenExpressionsExercise2
import atomictest.eq

fun temperature(temperature: Int): String =
  TODO()

fun main() {
  temperature(30) eq "Hot"
  temperature(10) eq "Cool"
  temperature(-30) eq "Freezing"
}