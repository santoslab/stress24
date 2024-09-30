// #Sireum #Logika
//@Logika: --manual --background type
import org.sireum._
import org.sireum.justification._
import org.sireum.justification.natded.prop._
import org.sireum.justification.natded.pred._

@pure def existential5(vowel: C => B @pure, covered: Z => B @pure, holds: (Z, C) => B @pure, gameOver: B): Unit = {
  Deduce(
    //@formatter:off
    (
      ∃{(s: Z) => covered(s) & ∃{(c: C) => vowel(c) & holds(s, c)}},
      ∃{(x: Z) => covered(x)} __>: !gameOver
    ) ⊢ (
      !gameOver
    )
    Proof(
      1  (  ∃{(s: Z) => covered(s) & ∃{(c: C) => vowel(c) & holds(s, c)}}  ) by Premise,
      2  (  ∃{(x: Z) => covered(x)} __>: !gameOver                            ) by Premise,
      3  SubProof {(a: Z) => (
        4  Assume(  covered(a) & ∃{(c: C) => vowel(c) & holds(a, c)}  ),
        5  (  covered(a)                                                   ) by AndE1(4),
        6  (  ∃{(x: Z) => covered(x)}                                      ) by ExistsI[Z](5)
      )},
      7  (  ∃{(x: Z) => covered(x)}                                        ) by ExistsE[Z](1, 3),
      8  (  !gameOver                                                      ) by ImplyE(2, 7),
    )
    //@formatter:on
  )
}
