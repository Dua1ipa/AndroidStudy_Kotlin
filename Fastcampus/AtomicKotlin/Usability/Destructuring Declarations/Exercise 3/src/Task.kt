// Destructuring/Task3.kt
package destructuringDeclarationsExercise3

data class Person(
  val name: String,
  // TODO: add property here
  val age: Int
)

fun displayPersonInfo(person: Person) {
  val (name, age) = person
// TODO
}

fun main() {
val person = Person("Alice",  30)
//  val person = Person("Alice", "Johnson", 30)
  displayPersonInfo(person)
}