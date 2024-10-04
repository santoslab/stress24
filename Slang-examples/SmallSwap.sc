// #Sireum #Logika
import org.sireum._

def swap(a: ZS, i:Z, j: Z) : Unit = {
  Contract(
    Requires(0 <= i, i < a.size, 0 <= j, j < a.size),
    Modifies(a),
    Ensures(
      a(i) == In(a)(j),
      a(j) == In(a)(i),
      ∀(a.indices)(k => k == i | k == j | a(k) == In(a)(k)),
      a.size == In(a).size
    )
  )
  val t: Z = a(i)
  Deduce(|- (t == In(a)(i)))
  a(i) = a(j)
  Deduce(|- (t == In(a)(i)))
  Deduce(|- (a(i) == In(a)(j)))
  a(j) = t
  Deduce(|- (t == In(a)(i)))
  Deduce(|- (a(j) == t))
  Deduce(|- (a(j) == In(a)(i)))
  Deduce(|- (a(i) == In(a)(j)))
}
