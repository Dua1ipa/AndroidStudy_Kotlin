// ClassDelegation/Task1.kt
package classDelegationExercise1
import atomictest.eq

interface Rectangle {
  fun paint(): String
  // TODO
}

class ButtonImage(
  val width: Int,
  val height: Int
) : Rectangle {
  override fun paint() =
    "painting ButtonImage($width, $height)"
  // TODO
}

interface MouseManager {
  fun clicked(): Boolean
  fun hovering(): Boolean
  // TODO
}

class UserInput : MouseManager {
  override fun clicked() = true
  override fun hovering() = true
  // TODO
}

class Button(
  val width: Int,
  val height: Int,
  val image: Rectangle =
    ButtonImage(width, height),
  val input: MouseManager = UserInput()
) : Rectangle by image, MouseManager by input

fun main() {
  val button = Button(10, 5)
  button.paint() eq
    "painting ButtonImage(10, 5)"
  button.clicked() eq true
  button.hovering() eq true
  // TODO
}