// #Sireum #Logika
//@Logika: --manual --background type
import org.sireum._
import org.sireum.justification._
import org.sireum.justification.natded.prop._
import org.sireum.justification.natded.pred._

@pure def universal4[T](healthy: T => B @pure, happy: T => B @pure): Unit = {
  Deduce(
    //@formatter:off
    ∀{(x: T) => healthy(x) __>: happy(x)}  ⊢  (∀{(y: T) => healthy(y)} __>: ∀{(x: T) => happy(x)})
    Proof(
      1  (  ∀{(x: T) => healthy(x) __>: happy(x)}               ) by Premise,
      2  SubProof(
        3  Assume(  ∀{(y: T) => healthy(y)}  ),
        4  SubProof {(a: T) => (
          5  (  healthy(a)                                   ) by AllE[T](3),
          6  (  healthy(a) __>: happy(a)                        ) by AllE[T](1),
          7  (  happy(a)                                     ) by ImplyE(6, 5)
        )},
        8  (  ∀{(x: T) => happy(x)}                          ) by AllI[T](4)
      ),
      9  (  ∀{(y: T) => healthy(y)} __>: ∀{(x: T) => happy(x)}  ) by ImplyI(2)
    )
    //@formatter:on
  )
}
