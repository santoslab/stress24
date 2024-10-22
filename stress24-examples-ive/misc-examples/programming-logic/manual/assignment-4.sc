// #Sireum #Logika
//@Logika: --manual --background save
import org.sireum._
import org.sireum.justification._

var hours: Z = 4

Deduce((  hours == 4  ) by Premise)

val minutes: Z = hours * 60

Deduce(
  //@formatter:off
  1  (  hours == 4             ) by Premise,
  2  (  minutes == hours * 60  ) by Premise,
  3  (  minutes == 240         ) by Algebra*(1, 2)
  //@formatter:on
)

hours = 5

Deduce(
  //@formatter:off
  1  (  hours == 5      ) by Premise,
  2  (  minutes == 240  ) by Premise
  //@formatter:on
)
