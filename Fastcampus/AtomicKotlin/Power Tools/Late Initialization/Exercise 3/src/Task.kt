// LateInitialization/LateInitSoln3.kt
package lateInitializationExercise3
import atomictest.*

data class Generator(val id: Int)

class Turbine(val id: Int) {
  private lateinit var _generator: Generator
  val generator: Generator = Generator(0) // TODO
  override fun toString() =
    "Generator $id running: " +
    TODO()
}

class PowerPlant(nTurbines: Int = 4) {
  private val turbines: List<Turbine> =
    TODO()
  fun generator(number: Int): Generator {
    TODO()
  }
  fun status() = "TODO"
}

fun main() {
  val plant = PowerPlant()
  plant.status()
  trace(plant.generator(1))
  trace(plant.generator(3))
  plant.status()
  trace eq """
    Generator 0 running: false
    Generator 1 running: false
    Generator 2 running: false
    Generator 3 running: false
    Generator(id=1)
    Generator(id=3)
    Generator 0 running: false
    Generator 1 running: true
    Generator 2 running: false
    Generator 3 running: true
  """
}