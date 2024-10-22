// #Sireum #Logika
//@Logika: --background save
import org.sireum._

def squareRec(a: ZS, i: Z): Unit = {
  Contract(
    Requires(0 <= i & i <= a.size),
    Modifies(a),
    Ensures(
      ∀(i until a.size)(j => a(j) == In(a)(j) * In(a)(j)),
      ∀(0 until i)(j => a(j) == In(a)(j))
    )
  )

  // decreases  a.size - i
  val decreasesEntry: Z = a.size - i

  if (i != a.size) {

    a(i) = a(i) * a(i)

    val decreasesRec: Z = a.size - (i + 1)

    assert(decreasesRec < decreasesEntry)
    assert(decreasesEntry > 0)

    squareRec(a, i + 1)
  }
}