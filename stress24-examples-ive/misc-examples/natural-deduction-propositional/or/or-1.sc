// #Sireum #Logika
//@Logika: --manual --background type

import org.sireum._
import org.sireum.justification._
import org.sireum.justification.natded.prop._

@pure def or1(p: B, q: B): Unit = {
  Deduce(
    //@formatter:off
    p  ⊢  (p | q)
    Proof(
      1  (  p      ) by Premise,
      2  (  p | q  ) by OrI1(1),
    )
    //@formatter:on
  )
}
