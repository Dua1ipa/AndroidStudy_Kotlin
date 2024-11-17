// NullableExtensions/Task1.kt
package extensionsForNullableTypesExercise1
import atomictest.eq

fun String?.orEmpty(): String = TODO()

fun main() {

  null.orEmpty() eq ""

  "abc".orEmpty() eq "abc"
}