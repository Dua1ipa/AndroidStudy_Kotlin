// Abstract/Task1.kt
package abstractClassesExercise1
import atomictest.trace

abstract class Shape

class Circle

class Square

class Triangle

fun main() {
  val shapes = listOf(
    Circle(),
    Square(),
    Triangle(),
  )
/*
  shapes.forEach { it.draw() }
  shapes.forEach { it.erase() }
*/
  trace eq """
    Draw Circle
    Draw Square
    Draw Triangle
    Erase Circle
    Erase Square
    Erase Triangle
  """
}