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

  if (i != a.size) {
    a(i) = a(i) * a(i)
    squareRec(a, i + 1)
  }
}