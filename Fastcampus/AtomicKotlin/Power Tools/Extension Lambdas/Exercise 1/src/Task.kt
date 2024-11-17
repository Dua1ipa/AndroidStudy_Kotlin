// ExtensionLambdas/ExtensionLambdasSoln1.kt
package extensionLambdasExercise1
import atomictest.eq

fun createString(
  build: StringBuilder.() -> Unit
): String {
  TODO()
}

fun <T> createList(
  build: ArrayList<T>.() -> Unit
): List<T> {
  TODO()
}

fun <K, V> createMap(
  build: HashMap<K, V>.() -> Unit
): Map<K, V> {
  TODO()
}

@OptIn(ExperimentalStdlibApi::class)
fun main() {
  createString {
    (1..10).forEach { append(it) }
  } eq buildString {
    (1..10).forEach { append(it) }
  }
  createList<String> {
    (1..10).forEach { add(it.toString()) }
  } eq buildList {
    (1..10).forEach { add(it.toString()) }
  }
  createMap<Char, Int> {
    ('a'..'j').forEachIndexed { n, c ->
      put(c, n)
    }
  } eq buildMap {
    ('a'..'j').forEachIndexed { n, c ->
      put(c, n)
    }
  }
}