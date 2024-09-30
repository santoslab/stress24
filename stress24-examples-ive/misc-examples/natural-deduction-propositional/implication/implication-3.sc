// #Sireum #Logika
//@Logika: --manual --background type

import org.sireum._
import org.sireum.justification._
import org.sireum.justification.natded.prop._

@pure def implication3(p: B, q: B, r: B): Unit = {
  Deduce(
    //@formatter:off
    (p,  q & p __>: r)  ⊢  (q __>: r)
    Proof(
      1  (  p             ) by Premise,
      2  (  q & p __>: r     ) by Premise,
      3  SubProof(
        4  Assume(  q  ),
        5  (  q & p       ) by AndI(4, 1),
        6  (  r           ) by ImplyE(2, 5)
      ),
      7  (  q __>: r         ) by ImplyI(3)
    )
    //@formatter:on
  )
}
