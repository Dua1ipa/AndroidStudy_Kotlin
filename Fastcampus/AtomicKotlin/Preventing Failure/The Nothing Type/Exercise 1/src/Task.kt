// NothingType/NothingTypeSoln1.kt
package theNothingTypeExercise1
import atomictest.*

class Failure

fun fail(msg: String): Nothing {
  TODO()
}

fun require(test: Boolean) {
  TODO()
}

fun check(test: Boolean) {
  TODO()
}

fun main() {
  require(true)
  check(true)
  capture {
    require(false)
  } eq "Failure: require() failed"
  capture {
    check(false)
  } eq "Failure: check() failed"
  trace eq """
    require() failed
    check() failed
  """
}