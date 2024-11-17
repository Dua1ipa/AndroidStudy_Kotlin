// LocalFunctions/Task2.kt
package localFunctionsExercise2
import atomictest.eq

fun createCounter(): Pair<() -> Unit, () -> Int> {
  TODO()
}

fun main() {
  val (inc, value) = createCounter()
  repeat(10) {
    inc()
  }
  value() eq 10
}