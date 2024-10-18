// #Sireum #Logika
import org.sireum._
import org.sireum.justification._

// Purely functional implementation of the minimum of two
// integer values.
@strictpure def min(x: Z, y: Z): Z = {
  if (x < y) { x } else { y }
}

// Simplification rule for min, case x < y.
@pure def minL(x: Z, y: Z): Unit = {
  Contract(
    Requires(x < y),
    Ensures(min(x, y) === x)
  )
  Deduce(
    1 (x < y) by Premise,
    2 (min(x, y) === x) by Simpl and 1
  )
}

// Simplification rule for min, case y <= x.
@pure def minR(x: Z, y: Z): Unit = {
  Contract(
    Requires(y <= x),
    Ensures(min(x, y) === y)
  )
  Deduce(
    1 (y <= x) by Premise,
    2 (!(x < y)) by Algebra,
    3 (min(x, y) === y) by Simpl and 2
  )
}

// Purely functional implementation of a function that calls min.
@strictpure def min0(x: Z): Z = min(x, x*0)

// Proposition about min0 using simplification and rewriting.
// 'minL _' can be removed from the rewriting set without affecting the proof
// because of the condition 0 <= x in line 2. The definition of min is not used
// in the proof, only property minR.
@pure def min0_prop(x: Z): Unit = {
  Deduce(
    (0 <= x) |- (min0(x) === 0) Proof (
      1 (min0(x) === min(x, 0)) by Simpl,
      2 (0 <= x) by Premise,
      3 (min0(x) === 0) by Rewrite(RS(minL _, minR _), 1) and 2
    )
  )
}