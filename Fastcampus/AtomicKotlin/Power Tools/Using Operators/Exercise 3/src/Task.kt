// UsingOperators/UsingOperatorsSoln3.kt
package usingOperatorsExercise3
import atomictest.trace
import kotlin.random.Random

data class N(var n: Int)

fun main() {
  val rnd = Random(47) // For repeatability
  TODO()
  trace eq """
    47
    null
    list: [N(4), N(0), N(2), N(3), N(1)]
    sorted set (1): [N(0), N(1), N(2), N(3), N(4)]
    sorted set (2): [N(2), N(1), N(2), N(3), N(4)]
  """
}