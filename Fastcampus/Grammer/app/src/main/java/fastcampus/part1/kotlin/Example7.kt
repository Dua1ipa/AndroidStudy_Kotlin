package fastcampus.part1.kotlin

fun main() {
    val list = mutableListOf(1, 2, 3, 4, 5)
    list.add(6)
    list.addAll(listOf(7, 8, 9))

    val list1 = listOf(1, 2, 3, 4)
    list1.get(0)
    list1[0]

    println(list1.map { it*10 }.joinToString("/"))

    val diverseList = listOf(1, "�ȳ�", 1.74, true)

    println(list.joinToString (","))

    val map = mapOf((1 to "�ȳ�"), (2 to "�ݰ���"))
    val map1 = mutableMapOf((1 to "�ȳ�"), (2 to "�ݰ���"))
    map1.put(3, "����")
    map1[4] = "�ݰ���"

}