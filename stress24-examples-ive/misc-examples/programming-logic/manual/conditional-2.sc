// #Sireum #Logika
//@Logika: --manual --background save
import org.sireum._
import org.sireum.justification._
import org.sireum.justification.natded.prop._

def max(x: Z, y: Z): Z = {
  Contract(
    Ensures(
      Res[Z] >= x & Res[Z] >= y & (Res[Z] == x | Res[Z] == y)
    )
  )

  var r: Z = 0

  if (x > y) {

    Deduce((  x > y  ) by Premise)

    r = x

    Deduce(
      //@formatter:off
      1  (  r == x  ) by Premise,
      2  (  x > y   ) by Premise,
      3  (  r >= x  ) by Algebra* 1,
      4  (  r > y   ) by Subst_>(1, 2),
      5  (  r >= y  ) by Algebra* 4
      //@formatter:on
    )

  } else {

    Deduce((  !(x > y)  ) by Premise)

    r = y

    Deduce(
      //@formatter:off
      1  (  r == y    ) by Premise,
      2  (  !(x > y)  ) by Premise,
      3  (  r >= y    ) by Algebra* 1,
      4  (  y >= x    ) by Algebra* 2,
      5  (  r >= x    ) by Subst_>(1, 4)
      //@formatter:on
    )

  }

  Deduce(
    //@formatter:off
    1  (  r >= x                               ) by Premise,
    2  (  r >= y                               ) by Premise,
    3  (  (r == x) | (r == y)                  ) by Premise,
    4  (  r >= x & r >= y                      ) by AndI(1, 2),
    5  (  r >= x & r >= y & (r == x | r == y)  ) by AndI(4, 3)
    //@formatter:on
  )

  return r
}

val x: Z = Z.read()
val y: Z = Z.read()

val r: Z = max(x, y)

Deduce(
  1  (  r >= x & r >= y & (r == x | r == y)  ) by Premise
)

assert(r >= x & r >= y & (r == x | r == y))