// #Sireum #Logika
import org.sireum._
import org.sireum.justification._

@strictpure def min(x: Z, y: Z): Z = {
  if (x < y) { x } else { y }
}

@pure def minL(x: Z, y: Z): Unit = {
  Contract(
    Requires(x < y),
    Ensures(min(x, y) === x)
  )
  // Add rewriting proof, otherwise unfolding and then SMT
}

@pure def minR(x: Z, y: Z): Unit = {
  Contract(
    Requires(y <= x),
    Ensures(min(x, y) === y)
  )
  // Add rewriting proof
}

@strictpure def min0(x: Z): Z = min(x, x*0)

@pure def min0_prop(x: Z): Unit = {
  Deduce(
    (0 <= x) |- (min0(x) === 0) Proof (
      1 (min0(x) === min(x, 0)) by Simpl,
      2 (0 <= x) by Premise,
      3 (min0(x) === 0) by Rewrite(RS(minL _, minR _), 1) and 2
    )
  )
}