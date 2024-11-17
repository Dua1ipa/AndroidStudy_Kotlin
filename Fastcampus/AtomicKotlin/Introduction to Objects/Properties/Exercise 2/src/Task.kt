// Properties/Task2.kt
package propertiesExercise2

class Robot {
  /*TODO*/

  fun right(steps: Int) {
    TODO()
  }

  fun left(steps: Int) {
    TODO()
  }

  fun down(steps: Int) {
    TODO()
  }

  fun up(steps: Int) {
    TODO()
  }

  fun getLocation(): String = TODO()
}

fun main() {
  val robot = Robot()
  println(robot.getLocation())
  robot.right(1)
  println(robot.getLocation())
  robot.down(2)
  println(robot.getLocation())
}
/* Output:
(0, 0)
(1, 0)
(1, 2)
*/