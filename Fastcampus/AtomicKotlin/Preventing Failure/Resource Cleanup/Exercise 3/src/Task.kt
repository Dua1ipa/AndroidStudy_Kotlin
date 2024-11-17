// ResourceCleanup/ResourceCleanupSoln3.kt
package resourceCleanupExercise3
import atomictest.*

class Cleanup : AutoCloseable {
  fun f() = trace("f()")
  fun g() = trace("g()")
  fun h() = trace("h()")
  override fun close() = trace("close()")
}

enum class Option { Normal, Return, Throw }

fun verifyClose(opt: Option) {
  TODO()
}

fun main() {
  verifyClose(Option.Normal)
  verifyClose(Option.Return)
  capture {
    verifyClose(Option.Throw)
  } eq "Exception"
  trace eq """
    f()
    g()
    h()
    close()
    f()
    close()
    f()
    close()
  """
}