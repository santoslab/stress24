// #Sireum #Logika
//@Logika: --background save
import org.sireum._

// Given a sequence and two indices,
// swap the elements at the given indices.
def swap(a: ZS, i: Z, j: Z): Unit = {  // Unit is like void in Java
  Contract(
    Requires(
      0 <= i & i < a.size, // i is a valid index
      0 <= j & j < a.size  // j is a valid index
    ),
    Modifies(a),           // documents a is modified
    Ensures(
      // note: In(a) is the value of a at procedure entry point
      a ≡ In(a)(i ~> In(a)(j), j ~> In(a)(i))
    )
  )
  val t: Z = a(i)
  a(i) = a(j)
  a(j) = t
}
