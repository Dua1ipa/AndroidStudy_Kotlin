// NestedClasses/NestedEx2.kt
package nestedClassesExercise2
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

class House: Cleanable("House") {
  override val parts = listOf(
    Bedroom("Master Bedroom"),
    Bedroom("Guest Bedroom")
  )
  class Bedroom(id: String): Cleanable(id) {
    override val parts =
      listOf(Closet(), Bathroom())
    class Closet: Cleanable("Closet") {
      override val parts = listOf(
        Shelf(), Shelf()/*TODO*/
      )
      class Shelf: Cleanable("Shelf")
      /*TODO*/
    }
    class Bathroom: Cleanable("Bathroom") {
      override val parts =
        listOf(Toilet(), Sink()/*TODO*/)
      class Toilet: Cleanable("Toilet")
      class Sink: Cleanable("Sink")
      /*TODO*/
    }
  }
}

fun main() {
  House().clean() eq """
    (((Shelf clean Shelf clean Drawer clean Drawer clean) Closet clean
     (Toilet clean Sink clean Bathtub clean) Bathroom clean
    ) Master Bedroom clean
     ((Shelf clean Shelf clean Drawer clean Drawer clean) Closet clean
     (Toilet clean Sink clean Bathtub clean) Bathroom clean
    ) Guest Bedroom clean
    ) House clean
  """.trimIndent()
}