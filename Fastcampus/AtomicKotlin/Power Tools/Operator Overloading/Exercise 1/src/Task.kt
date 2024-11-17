// OperatorOverloading/OpOverloadingSoln1.kt
package operatorOverloadingExercise1
import atomictest.*

class WrapRange(
  val range: IntRange,
  private var current: Int = range.first
) {
  init {
    // TODO
  }
  override fun toString() = "$current"
  operator fun inc(): WrapRange {
    TODO()
  }
  operator fun dec(): WrapRange {
    TODO()
  }
}

fun main() {
  val range = 2..5
  var wr = WrapRange(range)
  trace(wr)
  repeat(5) {
    wr++
    trace(wr)
  }
  trace("-")
  repeat(2) {
    wr--
    trace(wr)
  }
  trace eq "2 3 4 5 2 3 - 2 5"

  capture { WrapRange(range, 6) } eq
    "IllegalArgumentException: " +
    "'current' out of range: 6"
  capture { WrapRange(range, -1) } eq
    "IllegalArgumentException: " +
    "'current' out of range: -1"
}