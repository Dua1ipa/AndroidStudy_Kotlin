// ExtensionProperties/Task1.kt
package extensionPropertiesExercise1
import atomictest.eq

val <T> List<T>.secondOrNull: T?
  get() = TODO()

val <T> List<T>.penultimateOrNull: T?
  get() = TODO()

fun main() {
  val ints = listOf(1, 2, 3)
  ints.secondOrNull eq 2
  ints.penultimateOrNull eq 2

  val strings = listOf("a", "b", "c", "d")
  strings.secondOrNull eq "b"
  strings.penultimateOrNull eq "c"
}