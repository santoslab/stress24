// #Sireum #Logika
//@Logika: --background save
import org.sireum._

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

  val b: Z = add1(a)

  assert(b == a + 1)

}