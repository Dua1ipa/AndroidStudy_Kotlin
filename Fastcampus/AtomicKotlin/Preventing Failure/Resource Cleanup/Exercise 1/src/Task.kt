// ResourceCleanup/ResourceCleanupSoln1.kt
package resourceCleanupExercise1
import atomictest.trace
import java.io.File

val text = """
Swing low, sweet chariot
Coming for to carry me home
""".trim()

fun writeAndRead() {
  TODO()
}

fun main() {
  writeAndRead()
  trace eq """
    Swing low, sweet chariot
    Coming for to carry me home
    Swing low, sweet chariot
    Coming for to carry me home
  """
}