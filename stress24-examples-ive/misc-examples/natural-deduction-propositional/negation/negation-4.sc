// #Sireum #Logika
//@Logika: --manual --background type

import org.sireum._
import org.sireum.justification._
import org.sireum.justification.natded.prop._

@pure def negation4(p: B, q: B): Unit = {
  Deduce(
    //@formatter:off
    (!p)  ⊢  (p __>: q)
    Proof(
      1  (  !p            ) by Premise,
      2  SubProof(
        3  Assume(  p  ),
        4  (  F           ) by NegE(3, 1),
        5  (  q           ) by BottomE(4)
      ),
      6  (  p __>: q         ) by ImplyI(2),
    )
    //@formatter:on
  )
}
