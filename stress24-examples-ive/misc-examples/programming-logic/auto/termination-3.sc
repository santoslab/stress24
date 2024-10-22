// #Sireum #Logika
//@Logika: --background save
import org.sireum._
import org.sireum.justification._

def multRec(m: Z, n: Z): Z = {
  Contract(
    Requires(n >= 0),
    Ensures(Res[Z] == m * n)
  )

  // decreases  n
  val decreasesEntry: Z = n

  var r: Z = 0

  if (n != 0) {

    val decreasesRec: Z = n - 1

    Deduce(
      //@formatter:off
      1  (  decreasesRec < decreasesEntry  ) by Auto,
      2  (  decreasesEntry > 0             ) by Auto
      //@formatter:on
    )
    // or
    assert(decreasesRec < decreasesEntry)
    assert(decreasesEntry > 0)

    r = multRec(m, n - 1)
    r = m + r
  }

  return r
}

