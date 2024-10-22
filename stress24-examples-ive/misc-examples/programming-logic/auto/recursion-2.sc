// #Sireum #Logika
//@Logika: --background save
import org.sireum._

// Given a non-negative integer argument n,
// factorial computes n!, where n! is defined as follows
//   0! == 1
//   1! == 1 * 0!
//   2! == 2 * 1!
//   …
//   n! == n * (n – 1)!     (if n > 0)

@strictpure def f(n: Z): Z =
  if (n == 0) {
    1
  } else if (n > 0) {
    n * f(n - 1)
  } else {
    halt("Undefined")
  }

// Goal: return 1 * 2 * 3 * … * n
def factorialRec(n: Z): Z = {
  Contract(
    Requires(n >= 0),
    Ensures(Res[Z] == f(n))
  )

  var r: Z = 1

  if (n != 0) {
    r = factorialRec(n - 1)
    r = r * n
  }
  return r
}