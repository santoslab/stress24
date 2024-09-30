// #Sireum #Logika
//@Logika: --manual --background type

import org.sireum._
import org.sireum.justification._
import org.sireum.justification.natded.prop._

@pure def implication1(p: B, q: B, r: B): Unit = {
  Deduce(
    //@formatter:off
    (p | q __>: r,  q)  ⊢  r
    Proof(
      1  (  p | q __>: r  ) by Premise,
      2  (  q          ) by Premise,
      3  (  p | q      ) by OrI2(2),
      4  (  r          ) by ImplyE(1, 3),
    )
    //@formatter:on
  )
}
