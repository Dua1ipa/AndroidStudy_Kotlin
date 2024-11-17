// SecondaryConstructors/Task3.kt
package secondaryConstructorsExercise3
import atomictest.eq
import secondaryConstructorsExercise3.Material.*

enum class Material {
  Ceramic, Metal, Plastic
}

class GardenItem(
    val name: String
) {
  var material: Material = Plastic

  constructor(
      name: String, material: Material
  ) : this(name) {
    this.material = material
  }

  constructor(
      material: Material
  ) : this("Strange Thing", material)

  override fun toString() = "$material $name"
}

fun main() {
  GardenItem("Elf").material eq Plastic
  GardenItem("Snowman").name eq "Snowman"
  GardenItem("Gazing Ball", Metal) eq
    "Metal Gazing Ball"
  GardenItem(material = Ceramic) eq
    "Ceramic Strange Thing"
}