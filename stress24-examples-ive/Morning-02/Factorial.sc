// #Sireum #Logika
import org.sireum._

// Definition by cases, equational
// Specification of the factorial function

@strictpure def fac_spec(n: Z): Z = n match {
  case 0 => 1
  case m => m * fac_spec(m - 1)
}

// Implementation using while loop
@pure def fac_it(n: Z): Z = {
  Contract(
    Requires(n >= 0),
    Ensures(Res == fac_spec(n))
  )
  var x: Z = 1
  var m: Z = 0;
  while (m < n) {
    Invariant(
      Modifies(x, m),
      (m >= 0),
      (m <= n),
      (x == fac_spec(m))
    )
    Deduce(|- (m < n))
    Deduce(|- (m >= 0))
    Deduce(|- (x == fac_spec(m)))
    Deduce(|- (x * (m + 1) == fac_spec(m + 1)))
    m = m + 1
    Deduce(|- (x * m == fac_spec(m)))
    x = x * m
    Deduce(|- (x == fac_spec(m)))
  }
  Deduce(|- (m >= n))
  Deduce(|- (m <= n))
  return x
}