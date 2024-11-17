// NothingType/NothingTypeSoln2.kt
package theNothingTypeExercise2
import atomictest.*

object Log {
  // TODO
  fun add(msg: String) = "TODO"
  fun report(): List<String> = TODO()
}

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
  } eq "Failure: require failed"
  capture {
    check(false)
  } eq "Failure: check failed"
  Log.report() eq
    "[require failed, check failed]"
}