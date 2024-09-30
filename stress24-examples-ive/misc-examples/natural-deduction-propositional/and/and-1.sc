// #Sireum #Logika
//@Logika: --manual --background type

import org.sireum._
import org.sireum.justification._
import org.sireum.justification.natded.prop._

@pure def and1(p: B, q: B, r: B): Unit = {
  Deduce(
    //@formatter:off
    (p,  q,  r)  ⊢  (r & (q & p))
      Proof(
      1  (  p            ) by Premise,
      2  (  q            ) by Premise,
      3  (  r            ) by Premise,
      4  (  q & p        ) by AndI(2, 1),
      5  (  r & (q & p)  ) by AndI(3, 4),
    )
    //@formatter:on
  )
}
