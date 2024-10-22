// #Sireum #Logika
//@Logika: --background save
import org.sireum._
import org.sireum.justification._

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
def factorial(n: Z): Z = {
  Contract(
    Requires(n >= 0),
    Ensures(Res[Z] == f(n))
  )

  var r: Z = 1
  var i: Z = 0

  while (i < n) {
    Invariant(
      Modifies(r, i),
      r == f(i),
      i >= 0,
      i <= n
    )

    // decreases  n - i
    val decreasesPre: Z = n - i

    i = i + 1
    r = r * i

    val decreasesPost: Z = n - i

    Deduce(
      //@formatter:off
      1  (  decreasesPost < decreasesPre     ) by Auto,
      2  (  (decreasesPost <= 0) __>: !(i < n)  ) by Auto
      //@formatter:on
    )
  }

  return r
}