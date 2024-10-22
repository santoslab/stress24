// #Sireum #Logika
//@Logika: --manual --background save
import org.sireum._
import org.sireum.justification._

val x: Z = 0

Deduce((  x == 0  ) by Premise)

val y: Z = 2 + x

Deduce(
  //@formatter:off
  1  (  y == 2 + x  ) by Premise,
  2  (  x == 0      ) by Premise
  //@formatter:on
)

val z: Z = 2 + x

Deduce(
  //@formatter:off
  1  (  y == 2 + x  ) by Premise,
  2  (  z == 2 + x  ) by Premise,
  3  (  z == y      ) by Subst_>(1, 2)
  //@formatter:on
)

assert(z == y)