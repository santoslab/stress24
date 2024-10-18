// #Sireum #Logika
import org.sireum._
import org.sireum.justification._

// Inc contains some simple function definitions
// that we want to use for rewriting.
object Inc {
  @abs def inc1(x: Z): Z = x + 1
  @abs def inc2(x: Z): Z = x + 2

  // This is optional: Define a rewriting set consisting of inc1 and inc2.
  // Often it's also appropriate to use the exlicit 'RS(...)' expression
  // in proofs.
  @rw val incRS = RS(inc1 _, inc2 _)
}

// Proposition: Property of inc1 and inc2 proved using 'Rewrite(-, -)'.
@pure def inc_prop1(x: Z, y: Z): Unit = {
  Deduce(
    (Inc.inc2(x) === Inc.inc1(y)) |- (x + 1 === y) Proof(
      1 (Inc.inc2(x) === Inc.inc1(y)) by Premise,
      2 (x + 2 === y + 1) by Rewrite(Inc.incRS, 1) T,
      3 (x + 1 === y) by Algebra
    )
  )
}

// Proposition: Property of inc1 and inc2 proved using 'RSimpl(-)'.
@pure def inc_prop2(x: Z, y: Z): Unit = {
  import Inc._
  Deduce(
    (x + 1 === y) |- (inc2(x) === inc1(y)) Proof(
      1 (x + 1 === y) by Premise,
      2 (x + 1 + 1 === y + 1) by Simpl and 1,
      3 (x + 2 === y + 1) by Algebra and 2,
      4 (inc2(x) === inc1(y)) by RSimpl(incRS) and 3
    )
  )
}

// Proposition: Property of + proved using 'Subst_<(-, -)'.
@pure def subst_prop1(x: Z, y: Z, z: Z): Unit = {
  Deduce(
    (y === x + 1, z === y + 1) |- (z == x + 2) Proof(
      1 (y === x + 1) by Premise,
      2 (z === y + 1) by Premise,
      3 (z === (x + 1) + 1) by Subst_<(1, 2),
      4 (z === x + 2) by Algebra and 3
    )
  )
}

// Proposition: Property of + proved using 'Subst_>(-, -)'.
@pure def subst_prop2(x: Z, y: Z, z: Z): Unit = {
  Deduce(
    (x + 1 === y, z === y + 1) |- (z == x + 2) Proof(
      1 (x + 1 === y) by Premise,
      2 (z === y + 1) by Premise,
      3 (z === (x + 1) + 1) by Subst_>(1, 2),
      4 (z === x + 2) by Algebra and 3
    )
  )
}
