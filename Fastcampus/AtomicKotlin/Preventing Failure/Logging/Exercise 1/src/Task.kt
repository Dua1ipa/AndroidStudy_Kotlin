// Logging/LoggingSoln1.kt
package loggingExercise1
import atomictest.*
import atomiclog.Logger

class Level(
  val range: IntRange,
  private var level: Int = range.start
) {
  init {
    require(level in range) {
      "Initial level out of range: $level"
    }
  }
  val value: Int
    get() = level
  fun up(): Int {
    if (level + 1 in range)
      level++
    return level
  }
  fun down(): Int {
    if (level - 1 in range)
      level--
    return level
  }
  fun set(new: Int) {
    require(new in range) {
      "set() argument out of range: $new"
    }
    level = new
  }
}

class Pipe

fun main() {
/*
  val pipe = Pipe()
  (0..10).forEach {
    pipe.level.up()
    pipe.checkLevel()
  }
  pipe.logger.logFile.readText().trimIndent() eq
  """
  Trace: Level 2
  Debug: Level 4
  Info: Level 6
  Warn: Level 8
  Error: Level 10
  Error: Level 10
  """
*/
}