// ExtensionFunctions/Task2.kt
package extensionFunctionsExercise2
import atomictest.eq

fun Int.isOdd(): Boolean = TODO()

fun Int.isEven(): Boolean = TODO()

fun main() {
  1.isOdd() eq true
  2.isEven() eq true
  13.isEven() eq false
}