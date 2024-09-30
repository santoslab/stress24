// #Sireum #Logika
//@Logika: --manual --background type
import org.sireum._
import org.sireum.justification._
import org.sireum.justification.natded.prop._
import org.sireum.justification.natded.pred._

@pure def existential1[T](human: T => B @pure, mortal: T => B @pure, Socrates: T): Unit = {
  Deduce(
    //@formatter:off
    (human(Socrates),  mortal(Socrates))  ⊢  ∃{(x: T) => human(x) & mortal(x)}
    Proof(
      1  (  human(Socrates)                     ) by Premise,
      2  (  mortal(Socrates)                    ) by Premise,
      3  (  human(Socrates) & mortal(Socrates)  ) by AndI(1, 2),
      4  (  ∃{(x: T) => human(x) & mortal(x)}   ) by ExistsI[T](3)
    )
    //@formatter:on
  )
}
