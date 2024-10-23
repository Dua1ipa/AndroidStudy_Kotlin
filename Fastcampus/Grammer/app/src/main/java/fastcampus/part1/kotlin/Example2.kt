package fastcampus.part1.kotlin

fun main() {
    println("Hello World")
    println(test(1, c = 4))
    println(test1(1, b = 2))
    println(test2(id = "hong", nickName = "홍",name = "홍길동"))
}

// 2.함수
fun test(a: Int, b: Int = 2, c: Int = 3): Int {
    return a + b + c
}

fun test1(a:Int, b:Int) = a + b

fun test2(name: String, nickName:String, id:String): String{
    return "{이름: $name, 닉네임: $nickName, 아이디: $id}"
}