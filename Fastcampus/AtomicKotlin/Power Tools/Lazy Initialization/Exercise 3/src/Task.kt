// LazyInitialization/LazyInitSoln3.kt
package lazyInitializationExercise3
import atomictest.*

class A {
  init {
    // TODO
  }
  val b = B() // TODO
  val i = 1 // TODO
  fun f() = 0 // TODO
}

class B {
  init {
    // TODO
  }
  val j = 2 // TODO
  fun g() = 0 // TODO
}

fun main() {
  val a = A() // TODO
  trace("'a' defined")
  trace(a.f())
  trace eq """
    'a' defined
    Initializing A
    A constructor
    Initializing A.i
    Initializing A.b
    B constructor
    Initializing B.j
    3
  """
}