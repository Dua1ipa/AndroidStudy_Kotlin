// BreakAndContinue/Task2.kt
package breakAndContinueExercise2

fun analyzeStrings1(strings: List<List<String>>) {
  outer@ for (list in strings) {
    inner@ for (string in list) {
      if (string == "stop") break// add label
      println(string)
    }
  }
}

fun analyzeStrings2(strings: List<List<String>>) {
  outer@ for (list in strings) {
    inner@ for (string in list) {
      if (string == "stop") continue// add label
      println(string)
    }
  }
}

fun main() {
  val strings = listOf(
    listOf("a", "b", "stop", "z", "e"),
    listOf("first", "second", "stop", "ieuwg")
  )
  println("analyzeStrings1:")
  analyzeStrings1(strings)
  println("analyzeStrings2:")
  analyzeStrings2(strings)
}
/* Output:
analyzeStrings1:
a
b
first
second
analyzeStrings2:
a
b
first
second
 */