// #Sireum #Logika
//@Logika: --background save
import org.sireum._

def multRec(m: Z, n: Z): Z = {
  Contract(
    Requires(n >= 0),
    Ensures(Res[Z] == m * n)
  )

  var r: Z = 0

  if (n != 0) {
    r = multRec(m, n - 1)
    r = m + r
  }

  return r
}

