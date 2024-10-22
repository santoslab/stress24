// #Sireum #Logika
//@Logika: --manual --background save
import org.sireum._
import org.sireum.justification._

val hours: Z = Z.prompt("Type an int > 2: ")

assume(hours > 2)

Deduce(
  //@formatter:off
  1  (  hours > 2  ) by Premise,
  (  hours > 2     ) by Premise
  //@formatter:on
)
