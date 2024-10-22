// #Sireum #Logika
import org.sireum._
import org.sireum.justification._

// Provide the simplification rules minL, minR, maxL, maxR below,
// that make the proof fragment in function min_max succeed.
// You need to study the proof in min_max to determine the shape
// of the rules.

@pure def min(x: Z, y: Z, n: Z): Z = {
  Contract(
    Requires(n >= 0),
    Ensures(Res == x * n | Res == y * n, Res[Z] <= x * n, Res[Z] <= y * n)
  )
  if (x < y) {
    return x * n
  } else {
    return y * n
  }
}

@pure def minL(x: Z, y: Z, n: Z): Unit = {
  Contract(
    Requires(true),
    Ensures(true)
  )
}

@pure def minR(x: Z, y: Z, n: Z): Unit = {
  Contract(
    Requires(true),
    Ensures(true)
  )
}

@pure def max(x: Z, y: Z, n: Z): Z = {
  Contract(
    Requires(n >= 0),
    Ensures(Res == x * n | Res == y * n, x * n <= Res[Z], y * n <= Res[Z])
  )
  if (x < y) {
    return y * n
  } else {
    return x * n
  }
}

@pure def maxL(x: Z, y: Z, n: Z): Unit = {
  Contract(
    Requires(true),
    Ensures(true)
  )
}

@pure def maxR(x: Z, y: Z, n: Z): Unit = {
  Contract(
    Requires(true),
    Ensures(true)
  )
}

def min_max(x: Z, y: Z, z: Z, n: Z): Unit = {
  Contract(
    Requires(n >= 0),
    Ensures(min(x, max(y, z, 1), n) * n == max(min(x, y, n), min(x, z, n), n))
  )
  if (x < y) {
    if (x < z) {
      if (y < z) {
        Deduce(
          1 (n >= 0) by Premise,
          2 (x < y) by Premise,
          3 (x < z) by Premise,
          4 (y < z) by Premise,
          5 (1 >= 0) by Algebra,
          6 (max(y, z, 1) == z * 1) by RSimpl(RS(maxL _)) and (4, 5),
          7 (min(x, max(y, z, 1), n) == min(x, z, n)) by Simpl and 6,
          8 (min(x, z, n) == x * n) by RSimpl(RS(minL _)) and (1, 3)
        )
      } else {
        Deduce(
          1 (n >= 0) by Premise,
          2 (x < y) by Premise,
          3 (x < z) by Premise,
          4 (y >= z) by Auto,
          5 (1 >= 0) by Algebra,
          6 (max(y, z, 1) == y * 1) by RSimpl(RS(maxR _)) and (4, 5),
          7 (min(x, max(y, z, 1), n) == min(x, y, n)) by Simpl and 6,
          8 (min(x, z, n) == x * n) by RSimpl(RS(minL _)) and (1, 3)
        )
      }
    } else {
      Deduce(
        1 (n >= 0) by Premise,
        2 (x < y) by Premise,
        3 (x >= z) by Auto,
        4 (min(x, z, n) == z * n) by RSimpl(RS(minR _)) and (1, 3)
      )
    }
  } else {
  }
}