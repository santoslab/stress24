// #Sireum #Logika
//@Logika: --background save
import org.sireum._

val IntMax: Z = 2147483647
val a: Z = Z.read()
if (a < 0) {
  val b: Z = increase(a)
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