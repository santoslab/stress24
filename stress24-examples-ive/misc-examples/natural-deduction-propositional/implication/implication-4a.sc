// #Sireum #Logika
//@Logika: --manual --background type

import org.sireum._
import org.sireum.justification._
import org.sireum.justification.natded.prop._

@pure def implication4a(p: B, q: B): Unit = {
  Deduce(
    //@formatter:off
    q  ⊢  (p __>: q)
    Proof(
      1  (  q             ) by Premise,
      2  SubProof(
        3  Assume(  p  ),
        4  (  q           ) by Premise
      ),
      5  (  p __>: q         ) by ImplyI(2),
    )
    //@formatter:on
  )
}
