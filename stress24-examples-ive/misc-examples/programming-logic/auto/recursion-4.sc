// #Sireum #Logika
//@Logika: --background save
import org.sireum._

def squareRec(a: ZS, i: Z): Unit = {
  Contract(
    Requires(0 <= i),
    Modifies(a),
    Ensures(
      ∀(i until a.size)(j => a(j) == In(a)(j) * In(a)(j)),
      ∀(0 until i)(j => (j < a.size) ___>: (a(j) == In(a)(j))),
      a.size == In(a).size
    )
  )

  if (i < a.size) {
    a(i) = a(i) * a(i)
    squareRec(a, i + 1)
  }
}