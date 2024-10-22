// #Sireum #Logika
//@Logika: --manual --background save
import org.sireum._
import org.sireum.justification._

def absValue(x: Z): Z = {
  Contract(
    Requires(x != 0),
    Ensures(Res[Z] > 0)
  )

  var ans: Z = 0
  if (x < 0) {

    Deduce(
      //@formatter:off
      1  (  x != 0  ) by Premise,
      2  (  x < 0   ) by Premise
      //@formatter:on
    )

    ans = -x

    Deduce(
      //@formatter:off
      1  (  x < 0         ) by Premise,
      2  (  ans == -x     ) by Premise,
      3  (  ans + x == 0  ) by Algebra* 2,
      4  (  x < ans + x   ) by Subst_>(3, 1),
      5  (  ans > 0       ) by Algebra* 4
      //@formatter:on
    )

  } else {

    Deduce(
      //@formatter:off
      1  (  x != 0    ) by Premise,
      2  (  !(x < 0)  ) by Premise,
      3  (  x >= 0    ) by Algebra* 2,
      4  (  x > 0     ) by Algebra* (1, 3)
      //@formatter:on
    )

    ans = x

    Deduce(
      //@formatter:off
      1  (  ans == x  ) by Premise,
      2  (  x > 0     ) by Premise,
      3  (  ans > 0   ) by Subst_>(1, 2)
      //@formatter:on
    )
  }

  Deduce((  ans > 0  ) by Premise)

  return ans
}

val n: Z = Z.read()
if (n != 0) {

  Deduce((  n != 0  ) by Premise)

  val m: Z = absValue(n)

  Deduce((  m > 0  ) by Premise)

  assert(m > 0)
}