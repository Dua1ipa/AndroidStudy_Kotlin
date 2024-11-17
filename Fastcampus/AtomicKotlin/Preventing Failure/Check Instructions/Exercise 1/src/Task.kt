// CheckInstructions/CheckInstructionsSoln1.kt
package checkInstructionsExercise1
import atomictest.*

fun f(s: String): String {
  TODO()
}

fun g(d: Double): Double {
  TODO()
}

fun main() {
  capture { f("") } eq
    "IllegalArgumentException: " +
    "s must not be empty, is []"
  capture { f("   ") } eq
    "IllegalArgumentException: " +
    "s must not be blank, is [   ]"
  capture { f("abcdef") } eq
    "IllegalArgumentException: " +
    "s must contain 3 parts " +
    "separated by '-', is [abcdef]"
  capture { f("-abcdef-") } eq
    "IllegalArgumentException: " +
    "s must contain 3 parts " +
    "separated by '-', is [-abcdef-]"
  capture { f("-abc-def-") } eq
    "IllegalArgumentException: " +
    "s must contain 3 parts " +
    "separated by '-', is [-abc-def-]"
  f("ab-cd-ef") eq "ab-cd-ef"
  capture { g(-0.1) } eq
    "IllegalArgumentException: " +
    "Failed requirement."
  capture { g(11.0) } eq
    "IllegalArgumentException: " +
    "Failed requirement."
  g(5.5) eq 5.5
}