// ComplexConstructors/Exercise3.kt
package complexConstructorsExercise3
import atomictest.eq

class MultipleInit {
  val initOrder = mutableListOf<String>()
  init {
    // TODO
  }
  init {
    // TODO
  }
  init {
    // TODO
  }
}

fun main() {
  MultipleInit().initOrder eq "[one, two, three]"
}