// #Sireum #Logika
//@Logika: --manual --background type

import org.sireum._
import org.sireum.justification._
import org.sireum.justification.natded.prop._

@pure def negation6(p: B): Unit = {
  Deduce(
    //@formatter:off
    (!(!p))  ⊢  p
    Proof(
      1  (  !(!p)          ) by Premise,
      2  SubProof(
        3  Assume(  !p  ),
        4  (  F            ) by NegE(3, 1)
      ),
      5  (  p              ) by PbC(2),
    )
    //@formatter:on
  )
}
