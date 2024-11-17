// Objects/ObjectEx1.kt
package objectsExercise1
import atomictest.*

private fun f() = trace("f() $p")
private val p = "p"

object Space {
  /*TODO*/
}

private object Space2 {
  /*TODO*/
}

fun main() {
  f()
  /* Space.f()
  Space2.f() */
  trace eq """
    f() p
    Space.f() Space.p
    Space2.f() Space2.p
  """
}