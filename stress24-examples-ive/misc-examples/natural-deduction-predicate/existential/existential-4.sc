// #Sireum #Logika
//@Logika: --manual --background type
import org.sireum._
import org.sireum.justification._
import org.sireum.justification.natded.prop._
import org.sireum.justification.natded.pred._

@pure def existential4[T](human: T => B @pure, mortal: T => B @pure): Unit = {
  Deduce(
    //@formatter:off
    (
      ∀{(x: T) => human(x) __>: mortal(x)},
      ∃{(y: T) => human(y)}
    ) ⊢ (
      ∃{(z: T) => mortal(z)}
    )
    Proof(
      1  (  ∀{(x: T) => human(x) __>: mortal(x)}  ) by Premise,
      2  (  ∃{(y: T) => human(y)}              ) by Premise,
      3  SubProof {(a: T) => (
        4  Assume(  human(a)  ),
        5  (  human(a) __>: mortal(a)             ) by AllE[T](1),
        6  (  mortal(a)                        ) by ImplyE(5, 4),
        7  (  ∃{(z: T) => mortal(z)}           ) by ExistsI[T](6)
      )},
      8  (  ∃{(z: T) => mortal(z)}             ) by ExistsE[T](2, 3),
    )
    //@formatter:on
  )
}
