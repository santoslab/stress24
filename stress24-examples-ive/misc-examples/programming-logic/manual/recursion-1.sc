// #Sireum #Logika
//@Logika: --manual --background save
import org.sireum._
import org.sireum.justification._

def multRec(m: Z, n: Z): Z = {
  Contract(
    Requires(n >= 0),
    Ensures(Res[Z] == m * n)
  )

  var r: Z = 0

  // Start

  if (n != 0) {

    Deduce(
      //@formatter:off
      1  (  n >= 0      ) by Premise,
      2  (  n != 0      ) by Premise,
      3  (  n - 1 >= 0  ) by Algebra* (1, 2)
      //@formatter:on
    )

    r = multRec(m, n - 1)

    Deduce((  r == m * (n - 1)  ) by Premise)

    r = m + r

    Deduce(
      //@formatter:off
      1  (  Old(r) == m * (n - 1)  ) by Premise,
      2  (  r == m + Old(r)        ) by Premise,
      3  (  r == m + m * (n - 1)   ) by Subst_<(1, 2),
      4  (  r == m * n             ) by Algebra* 3
      //@formatter:on
    )

  } else {
    Deduce(
      //@formatter:off
      1  (  !(n != 0)   ) by Premise,
      2  (  r == 0      ) by Premise,
      3  (  r == m * n  ) by Algebra* (1, 2)
      //@formatter:on
    )
  }

  // End

  return r
}

