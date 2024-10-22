// #Sireum #Logika
//@Logika: --background save
import org.sireum._
import org.sireum.justification._

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
      a ≡ In(a)(i ~> In(a)(j), j ~> In(a)(i)) // sequence update notation
    )
  )
  val t: Z = a(i)
  a(i) = a(j)
  a(j) = t
}

val zs: ZS = ZS(1, 2, 3)

Deduce(
  //@formatter:off
  1  (  0 <= 0 & 0 < zs.size  ) by Auto, // obligation for the 1st swap's requires
  2  (  0 <= 2 & 2 < zs.size  ) by Auto // obligation for the 2nd swap's requires
  //@formatter:on
)

swap(zs, 0, 2)

assert(zs == ZS(3, 2, 1))