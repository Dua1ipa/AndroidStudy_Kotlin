package fastcampus.part1.kotlin

fun main() {
    var name: String = "홍길동"
    var num: Int = 10

    var nickName: String? = null

    val result = if (nickName == null){
        "값이 없음"
    }else{
        nickName
    }

    println(result)

    var result2 = nickName ?: "값이 없음"
    println(result2)

    val size = nickName?.length
    println(size)

}