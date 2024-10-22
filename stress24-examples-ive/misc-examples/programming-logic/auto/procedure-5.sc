// #Sireum #Logika
//@Logika: --background save
import org.sireum._
import org.sireum.justification._

def zero2(x: ZS): Unit = {
  Contract(
    Requires(x.size > 1),
    Modifies(x),
    Ensures(
      x ≡ In(x)(0 ~> 0, 1 ~> 0)
    )
  )
  x(0) = 0
  x(1) = 0
}

val a: ZS = ZS(1, 2, 3)

Deduce(
  //@formatter:off
  1  (  a ≡ ZS(1, 2, 3)  ) by Premise,
  2  (  a.size > 1       ) by Auto
  //@formatter:on
)

zero2(a)

Deduce(
  //@formatter:off
  1  (  a.size == Old(a).size       ) by Premise,
  2  (  a ≡ Old(a)(0 ~> 0)(1 ~> 0)  ) by Premise,
  3  (  Old(a) ≡ ZS(1, 2, 3)        ) by Premise,
  4  (  a(0) == 0                   ) by Auto* (2, 3),
  5  (  a(0) == 0                   ) by Auto* (2, 3),
  6  (  a(2) == 3                   ) by Auto* (2, 3),
  7  (  a == ZS(0, 0, 3)            ) by Auto* (2, 3)
  //@formatter:on
)
