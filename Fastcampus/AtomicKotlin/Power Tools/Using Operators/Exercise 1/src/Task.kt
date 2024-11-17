// UsingOperators/UsingOperatorsSoln1.kt
package usingOperatorsExercise1
import atomictest.trace

class DataMany

fun all() {
  TODO()
}

fun firstHalf() {
  TODO()
}

fun secondHalf() {
  TODO()
}

fun interspersed() {
  TODO()
}

fun main() {
  all()
  firstHalf()
  secondHalf()
  interspersed()
  trace eq """
    1 2 3 4 5 6 7 8 9 10
    1
    10
    1 2 3 4 5
    6 7 8 9 10
    1 3 5 7 9
  """
}