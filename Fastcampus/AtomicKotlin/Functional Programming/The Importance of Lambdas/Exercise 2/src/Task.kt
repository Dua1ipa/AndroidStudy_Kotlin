// ImportanceOfLambdas/Task2.kt
package theImportanceOfLambdasExercise2
import atomictest.eq

fun filterNonBlank(strings: List<String>): List<String> =
  TODO()

fun main() {
  filterNonBlank(listOf("", "a", "  ")) eq listOf("a")
}