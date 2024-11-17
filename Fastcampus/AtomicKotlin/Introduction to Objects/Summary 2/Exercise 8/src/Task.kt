// Summary2/Task8.kt
package summaryIIExercise8
import atomictest.eq

val romanNumeralToIntMap: Map<String, Int> = TODO()

fun convertFromRoman(roman: String): Int {
  TODO()
}

fun main() {
  convertFromRoman("XXIII") eq 23
  convertFromRoman("XLIV") eq 44
  convertFromRoman("C") eq 100
  convertFromRoman("MMXVIII") eq 2018
}