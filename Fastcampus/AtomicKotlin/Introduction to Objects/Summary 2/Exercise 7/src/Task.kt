// Summary2/Task7.kt
package summaryIIExercise7
import atomictest.eq

val intToRomanNumeralMap: Map<Int, String> = TODO()

fun convertToRoman(number: Int): String {
  TODO()
}

fun main() {
  convertToRoman(23) eq "XXIII"
  convertToRoman(44) eq "XLIV"
  convertToRoman(100) eq "C"
  convertToRoman(2018) eq "MMXVIII"
}