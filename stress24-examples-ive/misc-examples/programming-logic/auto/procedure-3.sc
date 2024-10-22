// #Sireum #Logika
//@Logika: --background save
import org.sireum._

def absValue(x: Z): Z = {
  Contract(
    Requires(x != 0),
    Ensures(Res[Z] > 0)
  )

  var ans: Z = 0

  if (x < 0) {
    ans = -x
  } else {
    ans = x
  }

  return ans
}

val n: Z = Z.read()
if (n != 0) {
  val m: Z = absValue(n)
  assert(m > 0)
}