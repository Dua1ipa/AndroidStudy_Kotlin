// Objects/ObjectEx3.kt
package objectsExercise3
import atomictest.*

interface AdventureGame {
  interface Environment
  interface Character
  val environment: Environment
  val characters: MutableList<Character>
  fun populate()
}

class Jungle : AdventureGame.Environment {
/*TODO*/
}

class Monkey : AdventureGame.Character {
/*TODO*/
}

class Snake : AdventureGame.Character {
/*TODO*/
}

object MonkeysVsSnakes : AdventureGame {
  override val environment =
    TODO("Not yet implemented")
  override val characters =
    mutableListOf<AdventureGame.Character>()
  override fun populate() {
    TODO("Not yet implemented")
  }
}

fun main() {
  MonkeysVsSnakes.populate()
  MonkeysVsSnakes.characters eq
    "[Monkey, Snake]"
}