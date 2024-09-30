// #Sireum #Logika
//@Logika: --manual --background type

import org.sireum._
import org.sireum.justification._
import org.sireum.justification.natded.prop._

@pure def negation3(p: B, q: B): Unit = {
  Deduce(
    //@formatter:off
    (p | q,  !p)  ⊢  q
    Proof(
      1  (  p | q         ) by Premise,
      2  (  !p            ) by Premise,
      3  SubProof(
        4  Assume(  p  ),
        5  (  F           ) by NegE(4, 2),
        6  (  q           ) by BottomE(5)
      ),
      7  SubProof(
        8  Assume(  q  )
      ),
      9  (  q             ) by OrE(1, 3, 7),
    )
    //@formatter:on
  )
}