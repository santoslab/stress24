// #Sireum #Logika
//@Logika: --manual --background save
import org.sireum._
import org.sireum.justification._
import org.sireum.justification.natded.prop._

var x: Z = Z.random

// the precondition is that x falls in the range, 1,2,..,99
assume(x > 0 & x < 100)

Deduce((  x > 0 & x < 100  ) by Premise)

x = x + 1

Deduce(
  //@formatter:off
  1  (  Old(x) > 0 & Old(x) < 100  ) by Premise,
  2  (  x == Old(x) + 1            ) by Premise,
  3  (  Old(x) > 0                 ) by AndE1(1),
  4  (  Old(x) + 1 > 1             ) by Algebra* 3,
  5  (  x > 1                      ) by Subst_>(2, 4),
  6  (  Old(x) < 100               ) by AndE2(1),
  7  (  Old(x) + 1 < 101           ) by Algebra* 6,
  8  (  x < 101                    ) by Subst_>(2, 7),
  9  (  x > 1 & x < 101            ) by AndI(5, 8)
  //@formatter:on
)

// the postcondition is that x falls in the range, 2,3,...,100
assert(x > 1 & x < 101)