package fastcampus.part1.kotlin

fun main() {
    max(10, 20)
    isHoliday("토")
}

fun max(a: Int, b: Int): Unit {
    val result = if (a > b) a else b
    println(result)
}

fun isHoliday(dayofweek: String) {
    val result = when (dayofweek) {
        "월",
        "화",
        "수",
        "목",
        "금" -> true
        "토",
        "일" -> false
        else -> "없는요일"
    }
    println(result)
}