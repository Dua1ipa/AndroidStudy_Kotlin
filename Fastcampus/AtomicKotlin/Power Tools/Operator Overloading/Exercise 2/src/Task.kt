// OperatorOverloading/OpOverloadingSoln2.kt
package operatorOverloadingExercise2
import atomictest.trace

enum class Count {
  Eeny, Meeny, Miney, Moe;
  companion object {
    val max = Moe
  }
}

operator fun Count.inc(): Count =
  TODO()

operator fun Count.dec(): Count =
  TODO()

fun main() {
  var c = Count.Eeny
  for (n in 1..4) {
    trace("${c++} -> $c")
  }
  trace("---------------")
  for (n in 1..4) {
    trace("${++c} -> $c")
  }
  trace("---------------")
  for (n in 1..4) {
    trace("${c--} -> $c")
  }
  trace("---------------")
  for (n in 1..4) {
    trace("${--c} -> $c")
  }
  trace eq """
    Eeny -> Meeny
    Meeny -> Miney
    Miney -> Moe
    Moe -> Eeny
    ---------------
    Meeny -> Meeny
    Miney -> Miney
    Moe -> Moe
    Eeny -> Eeny
    ---------------
    Eeny -> Moe
    Moe -> Miney
    Miney -> Meeny
    Meeny -> Eeny
    ---------------
    Moe -> Moe
    Miney -> Miney
    Meeny -> Meeny
    Eeny -> Eeny
  """
}