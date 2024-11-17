// DownCasting/DownCastEx1.kt
package downcastingExercise1
import atomictest.*

interface Base {
  fun f()
}

class Derived1 : Base {
  override fun f() { TODO() }
  fun g() { TODO() }
}

class Derived2 : Base {
  override fun f() { TODO() }
  fun h() { TODO() }
}

fun checkAndCall(b: Base) {
  TODO()
}

fun main() {
  checkAndCall(Derived1()) // Upcast
  checkAndCall(Derived2()) // Upcast
  trace eq """
    Derived1.f()
    Derived1.g()
    Derived2.f()
    Derived2.h()
  """
}