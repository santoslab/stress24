// #Sireum #Logika
//@Logika: --manual --background type

import org.sireum._
import org.sireum.justification._
import org.sireum.justification.natded.prop._

@pure def or3(p: B, q: B, r: B): Unit = {
  Deduce(
    //@formatter:off
    (p | q,  r)  ⊢  (p & r | q & r)
    Proof(
      1  (  p | q            ) by Premise,
      2  (  r                ) by Premise,
      3  SubProof(
        4  Assume(  p  ),
        5  (  p & r          ) by AndI(4, 2),
        6  (  p & r | q & r  ) by OrI1(5)
      ),
      7  SubProof(
        8  Assume(  q  ),
        9  (  q & r          ) by AndI(8, 2),
       10  (  p & r | q & r  ) by OrI2(9)
      ),
     11  (  p & r | q & r    ) by OrE(1, 3, 7)
    )
    //@formatter:on
  )
}