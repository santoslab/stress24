// #Sireum #Logika
//@Logika: --manual --background save
import org.sireum._
import org.sireum.justification._

// increase x by 1
def add1(x: Z): Z = {
  Contract(
    Requires(x > 0 & x < 100),
    Ensures(Res[Z] == x + 1)
  )
  val y: Z = x + 1
  return y
}

val a: Z = Z.read()
if (a > 0 & a < 100) {

  Deduce((  a > 0 & a < 100  ) by Premise)

  val b: Z = add1(a)

  Deduce((  b == a + 1  ) by Premise)

  assert(b == a + 1)
}