// #Sireum #Logika
import org.sireum._
import org.sireum.justification._

object Inc {
  @abs def inc1(x: Z): Z = x + 1
  @abs def inc2(x: Z): Z = x + 2

  @rw val incRS = RS(inc1 _, inc2 _)
}

@pure def inc_prop1(x: Z, y: Z): Unit = {
  Deduce(
    (Inc.inc2(x) === Inc.inc1(y)) |- (x + 1 === y) Proof(
      1 (Inc.inc2(x) === Inc.inc1(y)) by Premise,
      2 (x + 2 === y + 1) by Rewrite(Inc.incRS, 1) T,
      3 (x + 1 === y) by Algebra
    )
  )
}

@pure def inc_prop2(x: Z, y: Z): Unit = {
  import Inc._
  Deduce(
    (x + 1 === y) |- (inc2(x) === inc1(y)) Proof(
      1 (x + 1 === y) by Premise,
      2 (x + 2 === y + 1) by Algebra,
      3 (inc2(x) === inc1(y)) by RSimpl(incRS) and 2
    )
  )
}

// Third example with Simpl (and without incRS)