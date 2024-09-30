// #Sireum #Logika
//@Logika: --manual --background type

import org.sireum._
import org.sireum.justification._
import org.sireum.justification.natded.prop._
import org.sireum.justification.natded.pred._

@pure def existential2(vowel: C => B @pure, square: (Z, Z) => C @pure, holds: (C, C) => B @pure, e: C): Unit = {
  Deduce(
    //@formatter:off
    (vowel(e),  holds(square(1, 4), e))  ⊢  ∃{(y: C) => vowel(y) & ∃{(x: C) => holds(x, y)}}
    Proof(
      1  (  vowel(e)                                          ) by Premise,
      2  (  holds(square(1, 4), e)                            ) by Premise,
      3  (  ∃{(x: C) => holds(x, e)}                          ) by ExistsI[C](2),
      4  (  vowel(e) & ∃{(x: C) => holds(x, e)}               ) by AndI(1, 3),
      5  (  ∃{(y: C) => vowel(y) & ∃{(x: C) => holds(x, y)}}  ) by ExistsI[C](4)
    )
    //@formatter:on
  )
}