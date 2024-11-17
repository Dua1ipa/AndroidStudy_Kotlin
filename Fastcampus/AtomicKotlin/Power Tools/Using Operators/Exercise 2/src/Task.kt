// UsingOperators/UsingOperatorsSoln2.kt
package usingOperatorsExercise2
import atomictest.trace

fun create(): MutableList<Int> = TODO()

fun assignAppendIncrementContains() {
  TODO()
}

fun minus() {
  TODO()
}

fun plus() {
  TODO()
}

fun main() {
  assignAppendIncrementContains()
  minus()
  plus()
  trace eq """
    assignAppendIncrementContains()
    [0, 1, 2, 3, 4, 5, 6, 7, 8, 99, 101]
    true
    minus()
    [0, 1, 2, 3, 4, 6, 7, 8, 9]
    [0, 1, 2, 3, 4, 9]
    [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
    [2, 3, 4, 5, 6, 7, 8, 9]
    plus()
    [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 13, 14]
  """
}