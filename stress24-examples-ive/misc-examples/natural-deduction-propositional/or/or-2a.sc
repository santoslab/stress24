// #Sireum #Logika
//@Logika: --manual --background type

import org.sireum._
import org.sireum.justification._
import org.sireum.justification.natded.prop._

@pure def or2a(p: B, q: B): Unit = {
  Deduce(
    //@formatter:off
    (p & q)  ⊢  (p | q)
    Proof(
      1  (  p & q  ) by Premise,
      2  (  p      ) by AndE1(1),
      3  (  p | q  ) by OrI1(2),
    )
    //@formatter:on
  )
}