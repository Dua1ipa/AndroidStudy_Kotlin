// OperationsOnCollections/Task1.kt
package operationsOnCollectionsExercise1
import atomictest.eq

data class Person(val name: String, val age: Int)

fun findOldest(people: List<Person>, quantity: Int = 1): List<String> =
  TODO()

fun main() {
  val people = listOf(
    Person("Bob", 30),
    Person("Charlie", 20),
    Person("Alice", 25))
  findOldest(people, 1) eq listOf("Bob")
  findOldest(people, 2) eq listOf("Alice", "Bob")
}