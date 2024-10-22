// #Sireum #Logika
//@Logika: --manual --background save
import org.sireum._
import org.sireum.justification._

var x: Z = Z.prompt("Enter an integer > 0: ")

assume(x > 0)

Deduce((  x > 0  ) by Premise)

x = x + 1

Deduce(
  //@formatter:off
  1  (  x == Old(x) + 1  ) by Premise,
  2  (  Old(x) > 0       ) by Premise,
  3  (  Old(x) + 1 > 1   ) by Algebra* 2,
  4  (  x > 1            ) by Subst_>(1, 3)
  //@formatter:on
)

assert(x > 1)
