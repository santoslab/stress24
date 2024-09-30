// #Sireum #Logika
//@Logika: --manual --background type

import org.sireum._
import org.sireum.justification._
import org.sireum.justification.natded.prop._

@pure def or2b(p: B, q: B): Unit = {
  Deduce(
    //@formatter:off
    (p & q)  ⊢  (p | q)
    Proof(
      1  (  p & q  ) by Premise,
      2  (  q      ) by AndE2(1),
      3  (  p | q  ) by OrI2(2),
    )
    //@formatter:on
  )
}