// InnerClasses/InnerEx2.kt
package innerClassesExercise2
import atomictest.eq

class Box(contains: String, label: String) {
  // TODO
  fun manifest() =
    "TODO"
}

fun main() {
  Box("Computer", "Tasmania").manifest() eq
    "Computer: Tasmania Any-based"
}