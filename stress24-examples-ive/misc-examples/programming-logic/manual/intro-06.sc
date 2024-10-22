// #Sireum #Logika
//@Logika: --manual --background save
import org.sireum._
import org.sireum.justification._
import org.sireum.justification.natded.prop._

val x: Z = 1

Deduce((  x == 1  ) by Premise)

val y: Z = 2

Deduce(
  //@formatter:off
  1  (  x == 1           ) by Premise,
  2  (  y == 2           ) by Premise,
  3  (  x == 1 & y == 2  ) by AndI(1, 2)
  //@formatter:on
)

assert(x == 1 & y == 2)
