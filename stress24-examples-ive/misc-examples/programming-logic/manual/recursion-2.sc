// #Sireum #Logika
//@Logika: --manual --background save
import org.sireum._
import org.sireum.justification._
import org.sireum.justification.natded.prop._
import org.sireum.justification.natded.pred._

// Given a non-negative integer argument n,
// factorial computes n!, where n! is defined as follows
//   0! == 1
//   1! == 1 * 0!
//   2! == 2 * 1!
//   …
//   n! == n * (n – 1)!     (if n > 0)

@spec def f(n: Z): Z = $

// axioms
@spec def fFacts = Fact(
  f(0) == 1,
  ∀{(x: Z) => (x > 0) __>: (f(x) == f(x - 1) * x)}
)

// Goal: return 1 * 2 * 3 * … * n
def factorialRec(n: Z): Z = {
  Contract(
    Requires(n >= 0),
    Ensures(Res[Z] == f(n))
  )

  var r: Z = 1

  // Start

  if (n != 0) {

    Deduce(
      //@formatter:off
      1  (  n != 0      ) by Premise,
      2  (  n >= 0      ) by Premise,
      3  (  n - 1 >= 0  ) by Algebra* (1, 2)
      //@formatter:on
    )

    r = factorialRec(n - 1)

    Deduce((  r == f(n - 1)  ) by Premise)

    r = r * n

    Deduce(
      //@formatter:off
       1  (  Old(r) == f(n - 1)                              ) by Premise,
       2  (  r == Old(r) * n                                 ) by Premise,
       3  (  n != 0                                          ) by Premise,
       4  (  n >= 0                                          ) by Premise,
       5  (  r == f(n - 1) * n                               ) by Subst_<(1, 2),
       6  (  ∀{ (x: Z) => (x > 0) __>: (f(x) == f(x - 1) * x)}  ) by ClaimOf(fFacts _),
       7  (  (n > 0) __>: (f(n) == f(n - 1) * n)                ) by AllE[Z](6),
       8  (  n > 0                                           ) by Algebra* (3, 4),
       9  (  f(n) == f(n - 1) * n                            ) by ImplyE(7, 8),
      10  (  r == f(n)                                       ) by Algebra* (5, 9)
      //@formatter:on
    )
  } else {
    Deduce(
      //@formatter:off
      1  (  !(n != 0)  ) by Premise,
      2  (  r == 1     ) by Premise,
      3  (  f(0) == 1  ) by ClaimOf(fFacts _),
      4  (  r == f(n)  ) by Algebra* (1, 2, 3)
      //@formatter:on
    )
  }

  // End

  return r
}