// ClassDelegation/Task3.kt
package classDelegationExercise3
import atomictest.*

interface A {
  fun foo()
  fun bar()
}

interface B {
  fun foo()
  fun baz()
}

class AA

class BB

class Delegation

fun main() {
/*
  val d = Delegation(AA(), BB())
  d.foo()
  d.bar()
  d.baz()
*/
  trace eq
    """
      AA.foo()
      BB.foo()
      Delegation.foo()
      AA.bar()
      BB.baz()
    """
}