// #Sireum #Logika
//@Logika: --manual --background type

import org.sireum._
import org.sireum.justification._
import org.sireum.justification.natded.prop._

@pure def and2(p: B, q: B, r: B): Unit = {
  Deduce(
    //@formatter:off
    (p & (q & r))  ⊢  (r & p)
      Proof(
      1  (  p & (q & r)  ) by Premise,
      2  (  p            ) by AndE1(1),
      3  (  q & r        ) by AndE2(1),
      4  (  r            ) by AndE2(3),
      5  (  r & p        ) by AndI(4, 2),
    )
    //@formatter:on
  )
}
