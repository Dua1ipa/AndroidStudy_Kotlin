// LazyInitialization/LazyInitSoln2.kt
package lazyInitializationExercise2
import atomictest.*

class Outer {
  val s1: String = "TODO"
  val s2: String = "TODO"
  inner class Inner {
    val si: String = "TODO"
  }
}

fun main() {
  val o = Outer()
  trace("Outer created")
  val inner = o.Inner()
  trace("Inner created")
  trace("First access: inner.si:")
  trace(inner.si)
  trace("Second access: inner.si:")
  trace(inner.si)
  trace eq """
    Outer constructor
    Outer created
    Inner constructor
    Inner created
    First access: inner.si:
    Initializing Inner.si
    Initializing Outer.s2
    Initializing Outer.s1
    Howdy Hello Hi
    Second access: inner.si:
    Howdy Hello Hi
  """
}