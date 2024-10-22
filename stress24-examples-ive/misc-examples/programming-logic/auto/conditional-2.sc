// #Sireum #Logika
//@Logika: --background save
import org.sireum._

def max(x: Z, y: Z): Z = {
  Contract(
    Ensures(
      Res[Z] >= x & Res[Z] >= y & (Res[Z] == x | Res[Z] == y)
    )
  )

  var r: Z = 0

  if (x > y) {
    r = x
  } else {
    r = y
  }

  return r
}

val x: Z = Z.read()
val y: Z = Z.read()

val r: Z = max(x, y)

assert(r >= x & r >= y & (r == x | r == y))