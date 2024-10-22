// #Sireum #Logika
//@Logika: --manual --background save
import org.sireum._
import org.sireum.justification._

val IntMax: Z = 2147483647
val a: Z = Z.read()
if (a < 0) {

  Deduce(
    //@formatter:off
    1  (  a < 0                 ) by Premise,
    2  (  IntMax == 2147483647  ) by Premise,
    3  (  a < IntMax            ) by Algebra* (1, 2)
    //@formatter:on
  )

  val b: Z = increase(a)

  Deduce((  b == a + 1  ) by Premise)

  assert(b == a + 1)
}

def increase(x: Z): Z = {
  Contract(
    Requires(x < IntMax),
    Ensures(Res[Z] == x + 1)
  )

  val r: Z = x + 1
  return r
}