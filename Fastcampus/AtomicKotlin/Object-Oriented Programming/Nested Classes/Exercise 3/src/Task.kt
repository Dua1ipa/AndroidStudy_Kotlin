// NestedClasses/NestedEx3.kt
package nestedClassesExercise3
import atomictest.*

abstract class Cleanable(val id: String) {
  open val parts: List<Cleanable> = listOf()
  fun clean(): String {
    val text = "$id clean"
    if (parts.isEmpty()) return text
    return "${parts.joinToString(
      " ", "(", ")",
      transform = Cleanable::clean)} $text\n"
  }
}

class Shelf

class Closet

class Toilet

class Sink

class Bathroom

class Bedroom

class House : Cleanable("House") {
  /*TODO*/
}

fun main() {
  House().clean() eq """
  (((Shelf clean Shelf clean) Closet clean
   (Toilet clean Sink clean) Bathroom clean
  ) Master Bedroom clean
   ((Shelf clean Shelf clean) Closet clean
   (Toilet clean Sink clean) Bathroom clean
  ) Guest Bedroom clean
  ) House clean
  """
}