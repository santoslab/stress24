// #Sireum #Logika
//@Logika: --background save
import org.sireum._
import org.sireum.justification._

// Given integer arguments m and (non-negative) n,
// mult computes multiplication of m and n by using repeated addition.
def mult(m: Z, n: Z): Z = {
  Contract(
    Requires(n >= 0),
    Ensures(Res[Z] == m * n)
  )

  var r: Z = 0
  var i: Z = 0

  while (i != n) {
    Invariant(
      Modifies(r, i),
      r == m * i,
      0 <= i & i <= n
    )

    // decreases  n - i
    val decreasesPre: Z = n - i

    r = r + m
    i = i + 1

    val decreasesPost: Z = n - i

    Deduce(
      //@formatter:off
      1  (  decreasesPost < decreasesPre      ) by Auto,
      2  (  (decreasesPost <= 0) __>: !(i != n)  ) by Auto
      //@formatter:on
    )
    // or
    assert(decreasesPost < decreasesPre)
    assert(decreasesPost > 0 | !(i != n))
  }

  return r
}