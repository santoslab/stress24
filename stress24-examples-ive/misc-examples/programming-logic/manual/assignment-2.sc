// #Sireum #Logika
//@Logika: --manual --background save
import org.sireum._
import org.sireum.justification._

val hours: Z = Z.prompt("Type an int > 2: ")

assume(hours > 2)

Deduce(
  //@formatter:off
  (  hours > 2  ) by Premise
  //@formatter:on
)

val minutes: Z = hours * 60

Deduce(
  //@formatter:off
  1  (  hours > 2              ) by Premise,
  2  (  minutes == hours * 60  ) by Premise,
  3  (  minutes > 120          ) by Algebra*(1, 2)
  //@formatter:off
)

assert(minutes > 120)
