// DelegationTools/DelegToolsSoln3.kt
package propertyDelegationToolsExercise4
import atomictest.eq
import kotlin.properties.Delegates

class PositiveInt(i: Int) {
  var n: Int = TODO() // by ...
}

fun main() {
  var pi = PositiveInt(2)
  pi.n eq 2
  pi.n = 200
  pi.n eq 200
  pi.n = -1
  pi.n eq 200
}