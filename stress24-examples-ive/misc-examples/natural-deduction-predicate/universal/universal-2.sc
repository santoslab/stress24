// #Sireum #Logika
//@Logika: --manual --background type
import org.sireum._
import org.sireum.justification._
import org.sireum.justification.natded.prop._
import org.sireum.justification.natded.pred._

@pure def universal2[T](gt: (T, T) => B @pure, inc: T => T @pure, dec: T => T @pure): Unit = {
  Deduce(
    //@formatter:off
    (
      ∀{(x: T) => gt(inc(x), x)},
      ∀{(x: T) => gt(x, dec(x))}
    ) ⊢ (
      ∀{(x: T) => gt(inc(x), x) & gt(x, dec(x))}
    )
    Proof(
      1  (  ∀{(x: T) => gt(inc(x), x)}                  ) by Premise,
      2  (  ∀{(x: T) => gt(x, dec(x))}                  ) by Premise,
      3  SubProof {(a: T) => (
        4  (  gt(inc(a), a)                             ) by AllE[T](1),
        5  (  gt(a, dec(a))                             ) by AllE[T](2),
        6  (  gt(inc(a), a) & gt(a, dec(a))             ) by AndI(4, 5)
      )},
      7  (  ∀{(x: T) => gt(inc(x), x) & gt(x, dec(x))}  ) by AllI[T](3)
    )
    //@formatter:on
  )
}
