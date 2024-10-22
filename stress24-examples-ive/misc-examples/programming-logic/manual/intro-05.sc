// #Sireum #Logika
//@Logika: --manual --background save
import org.sireum._
import org.sireum.justification._

val x: Z = Z.prompt("Enter x: ")

val y: Z = Z.prompt("Enter y (< x): ")

assume(x > y)

Deduce((  x > y  ) by Premise)

val max: Z = x

Deduce(
  //@formatter:off
  1  (  max == x  ) by Premise,
  2  (  x > y     ) by Premise,
  3  (  max >= x  ) by Algebra* 1,
  4  (  max >= y  ) by Algebra* (3, 2)
  //@formatter:on
)

assert(max >= x)
assert(max >= y)

