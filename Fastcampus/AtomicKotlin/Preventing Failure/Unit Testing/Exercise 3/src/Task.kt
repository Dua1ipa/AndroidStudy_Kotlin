// Tests/unittesting/UnitTestingSoln3.kt
package unitTestingExercise3
import classdelegation.SpaceShipControls
import kotlin.test.*

infix fun <T> T.aeq(actual: T) =
  assertEquals(this, actual)

// TODO