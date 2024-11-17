// CompanionObjects/CompanionEx3.kt
package companionObjectsExercise3
import kotlin.random.Random
import atomictest.*

data class ID(
  val name: String,
  val id: String = idGenerator()
) {
  companion object {
    // TODO
    private fun idGenerator() =
      "TODO"
    fun test(n: Int) = List(n) {
      ID(('a' + it).toString())
    }.joinToString(",\n")
  }
}

class Bank(val name: String) {
  private val applied =
    mutableListOf<Account>()
  private val accounts =
    mutableListOf<Account>()
  private class Account(val name: String) {
    var id: ID? = null
    private var number: Long? = null
    fun addID(verifiedID: ID) {
      TODO()
    }
    companion object Numbers {
      // TODO
      private fun nextAccountNumber(): Long = TODO()
    }
    fun finish() {
      TODO()
    }
    override fun toString() = "$id $number"
  }
  fun applyForAccount(name: String) =
    "TODO"
  fun addID(id: ID) = "TODO"
  fun completeAccount(verifiedID: ID) {
    TODO()
  }
  override fun toString() =
    accounts.joinToString("\n")
}

fun main() {
  ID.test(4) eq """
  ID(name=a, id=weazblk9UF),
  ID(name=b, id=LCcPJtUGh5),
  ID(name=c, id=Acs5BzSj6m),
  ID(name=d, id=gHrshDvhwc)
  """
  val bank = Bank("Jerry's Savings & Loan")
  listOf(ID("Morty Smith"), ID("Beth Smith"),
    ID("Summer Smith")).forEach {
    bank.applyForAccount(it.name)
    bank.addID(it)
    bank.completeAccount(it)
  }
  bank eq """
  ID(name=Morty Smith, id=ePkc0HjTAU) 1000
  ID(name=Beth Smith, id=jjePlEO93w) 1001
  ID(name=Summer Smith, id=C8yaeFEgv6) 1002
  """
}