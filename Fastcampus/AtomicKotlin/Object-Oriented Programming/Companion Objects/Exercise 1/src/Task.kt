// CompanionObjects/CompanionEx1.kt
package companionObjectsExercise1
import atomictest.*

interface Vendor {
  fun pencil()
  fun pen()
}

interface VendorFactory {
  fun create(): Vendor
}

// TODO

fun main() {
  /*
// Implementations are interchangeable:
  consumer(Vendor1.factory)
  consumer(Vendor2.factory)
  trace eq """
  Vendor1 pencil
  Vendor1 pen
  Vendor2 pencil
  Vendor2 pen
  """
*/
}