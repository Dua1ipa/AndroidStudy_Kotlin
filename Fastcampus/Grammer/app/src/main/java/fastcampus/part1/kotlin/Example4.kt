package fastcampus.part1.kotlin

fun main() {
    val user = User("홍길동", 10)
    println(user.name)

    val kid = Kid("아기", 1, "여자")
}

// 부모 클래스
open class User(open val name: String, open var age: Int = 20)

// 자식 클래스 (부모 상속)
class Kid(override val name: String, override var age: Int) : User(name, age) {
    var gender: String = "남자"

    init {
        println("초기화 중 입니다.")
    }

    constructor(name: String, age: Int, gender: String) : this(name, age){
        this.gender = gender
        println("부생성자 호출")
    }
}