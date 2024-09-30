// #Sireum #Logika
//@Logika: --manual --background type

import org.sireum._
import org.sireum.justification._
import org.sireum.justification.natded.prop._

@pure def implication4c(p: B, q: B): Unit = {
  Deduce(
    //@formatter:off
    ⊢  (p __>: q __>: p)
    Proof(
      1  SubProof(
        2  Assume(  p  ),
        3  SubProof(
          4  Assume(  q  ),
          5  (  p           ) by Premise
        ),
        6  (  q __>: p         ) by ImplyI(3)
      ),
      7  (  p __>: q __>: p       ) by ImplyI(1),
    )
    //@formatter:on
  )
}