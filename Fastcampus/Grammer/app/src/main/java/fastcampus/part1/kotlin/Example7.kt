package fastcampus.part1.kotlin

fun main() {
    val list = mutableListOf(1, 2, 3, 4, 5)
    list.add(6)
    list.addAll(listOf(7, 8, 9))

    val list1 = listOf(1, 2, 3, 4)
    list1.get(0)
    list1[0]

    println(list1.map { it*10 }.joinToString("/"))

    val diverseList = listOf(1, "¾È³ç", 1.74, true)

    println(list.joinToString (","))

    val map = mapOf((1 to "¾È³ç"), (2 to "¹Ý°¡¿ö"))
    val map1 = mutableMapOf((1 to "¾È³ç"), (2 to "¹Ý°¡¿ö"))
    map1.put(3, "¤¾¤·")
    map1[4] = "¹Ý°¡¿ö"

}