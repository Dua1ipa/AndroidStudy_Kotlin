// WhenExpressions/Task1.kt
package whenExpressionsExercise1
import atomictest.capture
import atomictest.eq

fun cloudiness(cloudPercent: Int): String =
  TODO()

fun main() {
  cloudiness(100) eq "Cloudy"
  cloudiness(70) eq "Mostly Cloudy"
  cloudiness(50) eq "Partly Sunny"
  cloudiness(30) eq "Mostly Sunny"
  cloudiness(0) eq "Sunny"
  capture {
    cloudiness(1000)
  } eq "IllegalArgumentException: Cloudiness value should be between 0 and 100"
}