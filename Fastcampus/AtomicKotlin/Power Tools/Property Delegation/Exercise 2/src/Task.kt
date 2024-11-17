// PropertyDelegation/PropDelegationSoln3.kt
package propertyDelegationExercise2
import atomictest.eq
import kotlin.reflect.KProperty

class Delegator {
  private var list = List(8) { "$it" }
  var strings: List<String> = mutableListOf() // replace with 'by list'
  // TODO getValue and setValue extensions
}

fun main() {
  val x = Delegator()
  x.strings eq "[0, 1, 2, 3, 4, 5, 6, 7]"
  x.strings = listOf("99", "17")
  x.strings eq "[99, 17]"
}