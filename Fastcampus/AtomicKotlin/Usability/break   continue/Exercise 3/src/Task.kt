// BreakAndContinue/Task3.kt
package breakAndContinueExercise3

fun usingBreak(list: List<Int>) {
  println("before")
  for (i in list) {
    if (i == 0) break
    println("analyzing $i")
  }
  println("after")
}

fun analyzeList(list: List<Int>) {
  // TODO
}

fun usingContinue(list: List<Int>) {
  println("before")
  for (i in list) {
    if (i == 0) continue
    println("analyzing $i")
  }
  println("after")
}

fun analyzeElement(i: Int) {
  // TODO
}

fun main() {
  val list = listOf(1, 2, 0, 3, 4)
  println("Using break")
  usingBreak(list)
  println()
  println("Using continue")
  usingContinue(list)
}
/* Output:
Using break
before
analyzing 1
analyzing 2
after

Using continue
before
analyzing 1
analyzing 2
analyzing 3
analyzing 4
after
*/