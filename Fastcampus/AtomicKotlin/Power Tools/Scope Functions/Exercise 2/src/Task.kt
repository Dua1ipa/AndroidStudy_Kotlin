// ScopeFunctions/Task2.kt
package scopeFunctionsExercise2
import classdelegation.SpaceShipControls
import atomictest.*

fun main() {
  var velocity = 1
  with(SpaceShipControls()) {
    trace(forward(velocity))
    TODO()
    this
  }.let {
    TODO()
    it
  }.run {
    TODO()
    this
  }.apply {
    TODO()
  }.also {
    TODO()
  }
  trace eq """
    forward 1
    right 1
    down 1
    forward 2
    right 2
    down 2
    forward 3
    right 3
    down 3
    forward 4
    right 4
    down 4
    forward 5
    right 5
    down 5
  """
}