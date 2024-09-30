// #Sireum #Logika
//@Logika: --manual --background type

import org.sireum._
import org.sireum.justification._
import org.sireum.justification.natded.prop._

@pure def implication4b(p: B, q: B): Unit = {
  Deduce(
    //@formatter:off
    q  ⊢  (p __>: q)
    Proof(
      1  SubProof(
        2  Assume(  p  ),
        3  (  q           ) by Premise
      ),
      4  (  p __>: q         ) by ImplyI(1),
    )
    //@formatter:on
  )
}