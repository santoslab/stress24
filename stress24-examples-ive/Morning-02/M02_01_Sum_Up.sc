// #Sireum #Logika
import org.sireum._
import org.sireum.justification._

// Modify the function sum such that it ensures that it returns
// n * (n + 1) / 2. Add invariants that are sufficient to prove
// this. With the information available, Logika will complete
// the proof.
@pure def sum(n: Z): Z = {
  var m = 0
  var i = 0
  while (i < n) {
    Invariant(
      Modifies(m, i)
    )
    i = i + 1
    m = m + i
  }
  return m
}