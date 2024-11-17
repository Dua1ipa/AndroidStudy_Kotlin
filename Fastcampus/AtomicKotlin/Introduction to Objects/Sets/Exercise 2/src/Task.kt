// Sets/Task2.kt
package setsExercise2
import kotlin.random.Random

fun experiment(): Int {
  TODO()
}

fun main() {
  val counts = mutableListOf<Int>()
  repeat(1000) {
    counts += experiment()
  }
  println(counts.average())
}