// NothingType/NothingTypeSoln3.kt
package theNothingTypeExercise3
import atomictest.*

private var _debug: Boolean = TODO()

class Failure

fun fail(msg: String): Nothing =
  TODO()

fun debug(test: Boolean) {
  TODO()
}

fun main() {
  debug(true)
  capture {
    debug(false)
  } eq "Failure: debug() failed"
  _debug = false
  debug(true)
  debug(false)
  trace eq """
    debug(true) with _debug[true]
    debug(false) with _debug[true]
    debug(true) with _debug[false]
    debug(false) with _debug[false]
  """
}