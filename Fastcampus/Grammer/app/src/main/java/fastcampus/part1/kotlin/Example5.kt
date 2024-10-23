package fastcampus.part1.kotlin

fun main() {
    max(10, 20)
    isHoliday("��")
}

fun max(a: Int, b: Int): Unit {
    val result = if (a > b) a else b
    println(result)
}

fun isHoliday(dayofweek: String) {
    val result = when (dayofweek) {
        "��",
        "ȭ",
        "��",
        "��",
        "��" -> true
        "��",
        "��" -> false
        else -> "���¿���"
    }
    println(result)
}