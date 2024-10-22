// #Sireum #Logika
//@Logika: --background save
import org.sireum._
import org.sireum.justification._

@spec def P(n: Z): B = $ // an (uninterpreted) predicate P

// Convenient Notations for ∀ and ∃
def foo(a: ZS): Unit = {
  Deduce(
    1  (  ∀(0 until a.size)(x => P(x)) ≡ ∀{ (x: Z) => (0 <= x & x < a.size) ___>: P(x) }         ) by Auto T,
    2  (  ∀(0 to a.size - 1)(x => P(x)) ≡ ∀{ (x: Z) => (0 <= x & x <= a.size - 1) ___>: P(x) }   ) by Auto T,
    3  (  ∃(0 until a.size)(x => P(x)) ≡ ∃{ (x: Z) => (0 <= x & x < a.size) && P(x) }        ) by Auto T,
    4  (  ∃(0 to a.size - 1)(x => P(x)) ≡ ∃{ (x: Z) => (0 <= x & x <= a.size - 1) && P(x) }  ) by Auto T
  )
}