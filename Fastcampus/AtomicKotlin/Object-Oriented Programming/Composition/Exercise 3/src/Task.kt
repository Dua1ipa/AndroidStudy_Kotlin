// Composition/Task3.kt
package compositionExercise3
import atomictest.*

class Queue<E> {
  // private val arrayDeque
  fun put(e: E) = "TODO"
  fun poll(): E = TODO()
}

fun main() {
  val queue = Queue<String>()
  queue.put("a")
  queue.put("b")
  queue.put("c")
  queue.put("d")
  queue.poll() eq "a"
  queue.poll() eq "b"
}