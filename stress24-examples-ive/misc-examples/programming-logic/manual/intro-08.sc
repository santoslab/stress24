// #Sireum #Logika
//@Logika: --manual --background save
import org.sireum._
import org.sireum.justification._
import org.sireum.justification.natded.prop._

val x: Z = 1
val y: Z = 5

Deduce(
  //@formatter:off
  1  (  x == 1           ) by Premise,
  2  (  y == 5           ) by Premise,
  3  (  x == 1 & y == 5  ) by AndI(1, 2)
  //@formatter:on
)

assert(x == 1 & y == 5)

Deduce(
  //@formatter:off
  1  (  x == 1 & y == 5  ) by Premise,
  2  (  x == 1           ) by AndE1(1),
  3  (  y == 5           ) by AndE2(1)
  //@formatter:on
)

assert(x == 1)
assert(y == 5)
