// IntroGenerics/Task1.kt
package introductionToGenericsExercise1
import atomictest.eq

fun <T> List<T>.secondOrNull(): T? = TODO()

fun <T> List<T>.penultimateOrNull(): T? = TODO()

fun main() {
  val ints = listOf(1, 2, 3)
  ints.secondOrNull() eq 2
  ints.penultimateOrNull() eq 2

  val strings = listOf("a", "b", "c", "d")
  strings.secondOrNull() eq "b"
  strings.penultimateOrNull() eq "c"
}