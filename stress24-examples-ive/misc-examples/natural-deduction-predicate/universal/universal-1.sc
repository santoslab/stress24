// #Sireum #Logika
//@Logika: --manual --background type
import org.sireum._
import org.sireum.justification._
import org.sireum.justification.natded.prop._
import org.sireum.justification.natded.pred._

@pure def universal1[T](human: T => B @pure, mortal: T => B @pure, Socrates: T): Unit = {
  Deduce(
    //@formatter:off
    (
      ∀{(x: T) => human(x) __>: mortal(x)},
      human(Socrates)
    ) ⊢ (
      mortal(Socrates)
    )
    Proof(
      1  (  ∀{(x: T) => human(x) __>: mortal(x)}   ) by Premise,
      2  (  human(Socrates)                     ) by Premise,
      3  (  human(Socrates) __>: mortal(Socrates)  ) by AllE[T](1),
      4  (  mortal(Socrates)                    ) by ImplyE(3, 2)
    )
    //@formatter:on
  )
}