// Varargs/Task4.kt
package variableArgumentListsExercise4
import atomictest.eq

fun flatten(listOfLists: List<List<String>>): List<String> {
  TODO()
}

fun main() {
  val listOfLists = listOf(listOf("a", "b"), listOf("c", "d"))
  flatten(listOfLists) eq listOf("a", "b", "c", "d")
}