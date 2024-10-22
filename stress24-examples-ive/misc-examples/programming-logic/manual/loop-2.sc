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
  ∀{ (x: Z) => (x > 0) __>: (f(x) == f(x - 1) * x) }
)

// Goal: return 1 * 2 * 3 * … * n
def factorial(n: Z): Z = {
  Contract(
    Requires(n >= 0),
    Ensures(Res[Z] == f(n))
  )

  var r: Z = 1
  var i: Z = 0

  Deduce(
    //@formatter:off
    1  (  r == 1     ) by Premise,
    2  (  i == 0     ) by Premise,
    3  (  n >= 0     ) by Premise,
    4  (  f(0) == 1  ) by ClaimOf(fFacts _),
    5  (  r == f(i)  ) by Algebra* (1, 2, 4),
    6  (  i >= 0     ) by Algebra* 2,
    7  (  i <= n     ) by Algebra* (3, 2)
    //@formatter:on
  )

  while (i < n) {
    Invariant(
      Modifies(r, i),
      r == f(i),
      i >= 0,
      i <= n
    )

    i = i + 1

    Deduce(
      //@formatter:off
      1  (  i == Old(i) + 1  ) by Premise,
      2  (  r == f(Old(i))   ) by Premise,
      3  (  Old(i) >= 0      ) by Premise,
      4  (  Old(i) < n       ) by Premise,
      5  (  r == f(i - 1)    ) by Algebra* (1, 2),
      6  (  i > 0            ) by Algebra* (1, 3),
      7  (  i <= n           ) by Algebra* (4, 1)
      //@formatter:on
    )

    r = r * i

    Deduce(
      //@formatter:off
      1  (  r == Old(r) * i                                 ) by Premise,
      2  (  Old(r) == f(i - 1)                              ) by Premise,
      3  (  i > 0                                           ) by Premise,
      4  (  i >= 0                                          ) by Algebra* 3,
      5  (  r == f(i - 1) * i                               ) by Algebra* (1, 2),
      6  (  ∀{ (x: Z) => (x > 0) __>: (f(x) == f(x - 1) * x)}  ) by ClaimOf(fFacts _),
      7  (  (i > 0) __>: (f(i) == f(i - 1) * i)                ) by AllE[Z](6),
      8  (  f(i) == f(i - 1) * i                            ) by ImplyE(7, 3),
      9  (  r == f(i)                                       ) by Subst_>(8, 5)
      //@formatter:on
    )
  }

  Deduce(
    //@formatter:off
    1  (  r == f(i)  ) by Premise,
    2  (  !(i < n)   ) by Premise,
    3  (  i <= n     ) by Premise,
    4  (  r == f(n)  ) by Algebra* (1, 2, 3)
    //@formatter:on
  )
  return r
}