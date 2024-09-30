// #Sireum #Logika
//@Logika: --manual --background type

import org.sireum._
import org.sireum.justification._
import org.sireum.justification.natded.prop._

@pure def negation2(p: B, q: B): Unit = {
  Deduce(
    //@formatter:off
    (p,  q __>: !p)  ⊢  (!q)
    Proof(
      1  (  p             ) by Premise,
      2  (  q __>: !p        ) by Premise,
      3  SubProof(
        4  Assume(  q  ),
        5  (  !p          ) by ImplyE(2, 4),
        6  (  F           ) by NegE(1, 5)
      ),
      7  (  !q            ) by NegI(3),
    )
    //@formatter:on
  )
}