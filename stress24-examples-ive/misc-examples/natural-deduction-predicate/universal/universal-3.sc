// #Sireum #Logika
//@Logika: --manual --background type
import org.sireum._
import org.sireum.justification._
import org.sireum.justification.natded.prop._
import org.sireum.justification.natded.pred._

@pure def universal3[T](human: T => B @pure, mortal: T => B @pure, soul: T => B @pure): Unit = {
  Deduce(
    //@formatter:off
    (
      ∀{(x: T) => human(x) __>: mortal(x)},
      ∀{(y: T) => mortal(y) __>: soul(y)}
    ) ⊢ (
      ∀{(x: T) => human(x) __>: soul(x)}
    )
    Proof(
      1  (  ∀{(x: T) => human(x) __>: mortal(x)}  ) by Premise,
      2  (  ∀{(y: T) => mortal(y) __>: soul(y)}   ) by Premise,
      3  SubProof {(a: T) => (
        4  SubProof(
          5  Assume(  human(a)  ),
          6  (  human(a) __>: mortal(a)           ) by AllE[T](1),
          7  (  mortal(a)                      ) by ImplyE(6, 5),
          8  (  mortal(a) __>: soul(a)            ) by AllE[T](2),
          9  (  soul(a)                        ) by ImplyE(8, 7)
        ),
       10  (  human(a) __>: soul(a)               ) by ImplyI(4)
      )},
      11  (  ∀{(x: T) => human(x) __>: soul(x)}   ) by AllI[T](3)
    )
    //@formatter:on
  )
}