// Sequences/Task6.kt
package sequencesExercise6
import atomictest.eq

fun from(n: Int): Sequence<Int> = TODO()

fun main() {
  from(10).take(2).toList() eq listOf(10, 11)
}